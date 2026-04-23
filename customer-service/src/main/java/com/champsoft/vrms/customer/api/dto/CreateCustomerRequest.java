package com.champsoft.vrms.customer.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateCustomerRequest {
    @NotBlank
    private String fullName;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String phone;
    @NotBlank
    private String licenseNumber;
    @NotNull
    @Future
    private LocalDate licenseExpiryDate;
    @NotNull
    private Boolean eligibleToRent;
}
