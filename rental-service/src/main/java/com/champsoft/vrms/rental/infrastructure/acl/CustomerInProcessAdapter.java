package com.champsoft.vrms.rental.infrastructure.acl;

import lombok.RequiredArgsConstructor;
import com.champsoft.vrms.customer.domain.model.Customer;
import com.champsoft.vrms.customer.service.CustomerApplicationService;
import com.champsoft.vrms.rental.application.port.out.CustomerPort;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "rental.integration", name = "mode", havingValue = "in-process", matchIfMissing = false)
public class CustomerInProcessAdapter implements CustomerPort {

    private final CustomerApplicationService customerService;

    @Override
    public Customer getById(Long customerId) {
        return customerService.getDomainById(customerId);
    }
}
