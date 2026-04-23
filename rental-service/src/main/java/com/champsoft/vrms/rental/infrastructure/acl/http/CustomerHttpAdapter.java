package com.champsoft.vrms.rental.infrastructure.acl.http;

import lombok.RequiredArgsConstructor;
import com.champsoft.vrms.customer.api.dto.CustomerResponse;
import com.champsoft.vrms.customer.domain.model.Customer;
import com.champsoft.vrms.rental.application.port.out.CustomerPort;
import com.champsoft.vrms.rental.shared.exception.ResourceNotFoundException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "rental.integration", name = "mode", havingValue = "http", matchIfMissing = true)
public class CustomerHttpAdapter implements CustomerPort {

    private final RestTemplate restTemplate;
    private final RentalHttpProperties properties;

    @Override
    public Customer getById(Long customerId) {
        String url = properties.getCustomerBaseUrl() + "/" + customerId;
        ResponseEntity<CustomerResponse> response = restTemplate.getForEntity(url, CustomerResponse.class);
        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new ResourceNotFoundException("Customer not found.");
        }
        return HttpAclMapper.toDomain(response.getBody());
    }
}
