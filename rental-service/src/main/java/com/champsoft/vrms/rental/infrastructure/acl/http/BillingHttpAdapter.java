package com.champsoft.vrms.rental.infrastructure.acl.http;

import lombok.RequiredArgsConstructor;
import com.champsoft.vrms.billing.api.dto.CreateInvoiceRequest;
import com.champsoft.vrms.billing.api.dto.InvoiceResponse;
import com.champsoft.vrms.billing.domain.model.Invoice;
import com.champsoft.vrms.rental.application.port.out.BillingPort;
import com.champsoft.vrms.rental.shared.exception.BusinessRuleException;
import com.champsoft.vrms.rental.shared.exception.ResourceNotFoundException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "rental.integration", name = "mode", havingValue = "http", matchIfMissing = true)
public class BillingHttpAdapter implements BillingPort {

    private final RestTemplate restTemplate;
    private final RentalHttpProperties properties;

    @Override
    public Invoice createFromAmount(BigDecimal amount) {
        CreateInvoiceRequest request = new CreateInvoiceRequest();
        request.setRentalId(null);
        request.setAmount(amount);
        request.setPaymentStatus("PENDING");
        request.setPaymentDate(null);

        ResponseEntity<InvoiceResponse> response = restTemplate.postForEntity(
                properties.getBillingBaseUrl(),
                request,
                InvoiceResponse.class
        );

        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new BusinessRuleException("Failed to create invoice through billing service.");
        }
        return HttpAclMapper.toDomain(response.getBody());
    }

    @Override
    public Invoice getById(Long invoiceId) {
        String url = properties.getBillingBaseUrl() + "/" + invoiceId;
        ResponseEntity<InvoiceResponse> response = restTemplate.getForEntity(url, InvoiceResponse.class);
        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new ResourceNotFoundException("Invoice not found.");
        }
        return HttpAclMapper.toDomain(response.getBody());
    }
}
