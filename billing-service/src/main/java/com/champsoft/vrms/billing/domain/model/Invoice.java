package com.champsoft.vrms.billing.domain.model;

import lombok.*;
import com.champsoft.vrms.billing.shared.exception.BusinessRuleException;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice {
    private Long invoiceId;
    private Long rentalId;
    private BigDecimal amount;
    private String paymentStatus;
    private LocalDate paymentDate;
    public void validate() {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessRuleException("Invoice amount must be greater than 0.");
        }
    }
    public void registerPayment(LocalDate paymentDate) {
        if ("PAID".equalsIgnoreCase(paymentStatus)) {
            throw new BusinessRuleException("Invoice is already paid.");
        }
        this.paymentStatus = "PAID";
        this.paymentDate = paymentDate;
    }
}
