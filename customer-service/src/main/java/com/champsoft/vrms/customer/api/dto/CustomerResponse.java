package com.champsoft.vrms.customer.api.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CustomerResponse {
    private Long customerId;
    private String fullName;
    private String email;
    private String phone;
    private String licenseNumber;
    private LocalDate licenseExpiryDate;
    private Boolean eligibleToRent;
}
