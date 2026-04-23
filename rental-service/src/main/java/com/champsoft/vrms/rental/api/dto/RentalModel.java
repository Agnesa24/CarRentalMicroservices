package com.champsoft.vrms.rental.api.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class RentalModel extends RepresentationModel<RentalModel> {
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
