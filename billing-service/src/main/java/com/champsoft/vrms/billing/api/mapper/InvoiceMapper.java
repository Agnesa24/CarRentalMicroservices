package com.champsoft.vrms.billing.api.mapper;

import com.champsoft.vrms.billing.api.dto.CreateInvoiceRequest;
import com.champsoft.vrms.billing.api.dto.InvoiceResponse;
import com.champsoft.vrms.billing.domain.model.Invoice;
import com.champsoft.vrms.billing.persistence.entity.InvoiceJpaEntity;

public class InvoiceMapper {
    private InvoiceMapper() {}
    public static Invoice toDomain(CreateInvoiceRequest request) {
        return Invoice.builder()
                .rentalId(request.getRentalId())
                .amount(request.getAmount())
                .paymentStatus(request.getPaymentStatus())
                .paymentDate(request.getPaymentDate())
                .build();
    }
    public static Invoice toDomain(InvoiceJpaEntity entity) {
        return Invoice.builder()
                .invoiceId(entity.getInvoiceId())
                .rentalId(entity.getRentalId())
                .amount(entity.getAmount())
                .paymentStatus(entity.getPaymentStatus())
                .paymentDate(entity.getPaymentDate())
                .build();
    }
    public static InvoiceJpaEntity toJpa(Invoice invoice) {
        return InvoiceJpaEntity.builder()
                .invoiceId(invoice.getInvoiceId())
                .rentalId(invoice.getRentalId())
                .amount(invoice.getAmount())
                .paymentStatus(invoice.getPaymentStatus())
                .paymentDate(invoice.getPaymentDate())
                .build();
    }

    public static InvoiceResponse toResponse(Invoice invoice) {
        return InvoiceResponse.builder()
                .invoiceId(invoice.getInvoiceId())
                .rentalId(invoice.getRentalId())
                .amount(invoice.getAmount())
                .paymentStatus(invoice.getPaymentStatus())
                .paymentDate(invoice.getPaymentDate())
                .build();
    }
}
