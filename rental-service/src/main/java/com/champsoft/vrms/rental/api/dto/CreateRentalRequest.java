package com.champsoft.vrms.rental.api.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateRentalRequest {
    @NotNull
    private Long customerId;
    @NotNull
    private Long vehicleId;
    @NotNull
    private LocalDate startDate;
    @NotNull
    @Future
    private LocalDate endDate;
}
