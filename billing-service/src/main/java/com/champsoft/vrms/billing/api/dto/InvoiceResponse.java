package com.champsoft.vrms.billing.api.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class InvoiceResponse {
    private Long invoiceId;
    private Long rentalId;
    private BigDecimal amount;
    private String paymentStatus;
    private LocalDate paymentDate;
}
