package com.champsoft.vrms.rental.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateRentalRequest {
    @NotNull
    private String status;
}