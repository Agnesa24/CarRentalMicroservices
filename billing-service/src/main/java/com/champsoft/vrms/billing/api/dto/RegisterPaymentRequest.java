package com.champsoft.vrms.billing.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterPaymentRequest {
    @NotNull
    private LocalDate paymentDate;
}

