package com.champsoft.vrms.rental.infrastructure.acl;

import lombok.RequiredArgsConstructor;
import com.champsoft.vrms.billing.service.InvoiceApplicationService;
import com.champsoft.vrms.billing.domain.model.Invoice;
import com.champsoft.vrms.rental.application.port.out.BillingPort;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "rental.integration", name = "mode", havingValue = "in-process", matchIfMissing = false)
public class BillingInProcessAdapter implements BillingPort {

    private final InvoiceApplicationService invoiceService;

    @Override
    public Invoice createFromAmount(BigDecimal amount) {
        return invoiceService.createFromAmount(amount);
    }

    @Override
    public Invoice getById(Long invoiceId) {
        return invoiceService.getDomainById(invoiceId);
    }
}
