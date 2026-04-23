package com.champsoft.vrms.rental.application.port.out;

import com.champsoft.vrms.billing.domain.model.Invoice;

import java.math.BigDecimal;

public interface BillingPort {
    Invoice createFromAmount(BigDecimal amount);

    Invoice getById(Long invoiceId);
}
