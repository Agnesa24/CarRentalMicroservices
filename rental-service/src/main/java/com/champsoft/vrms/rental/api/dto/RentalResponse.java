package com.champsoft.vrms.rental.api.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class RentalResponse {
    private Long rentalId;
    private Long customerId;
    private String customerName;
    private Long vehicleId;
    private String vehicleName;
    private Long invoiceId;
    private BigDecimal amount;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
}
