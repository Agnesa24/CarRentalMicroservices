package com.champsoft.vrms.customer.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private Long customerId;
    private String fullName;
    private String email;
    private String phone;
    private String licenseNumber;
    private LocalDate licenseExpiryDate;
    private Boolean eligibleToRent;

    public void validate() {
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Full name is required.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email is required.");
        }
        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("Phone is required.");
        }
        if (licenseNumber == null || licenseNumber.isBlank()) {
            throw new IllegalArgumentException("License number is required.");
        }
        if (licenseExpiryDate == null) {
            throw new IllegalArgumentException("License expiry date is required.");
        }
        if (eligibleToRent == null) {
            throw new IllegalArgumentException("Eligible to rent is required.");
        }
    }
}