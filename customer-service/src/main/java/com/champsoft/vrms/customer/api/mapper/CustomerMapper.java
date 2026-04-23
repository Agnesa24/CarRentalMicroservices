package com.champsoft.vrms.customer.api.mapper;

import com.champsoft.vrms.customer.api.dto.CreateCustomerRequest;
import com.champsoft.vrms.customer.api.dto.CustomerResponse;
import com.champsoft.vrms.customer.domain.model.Customer;
import com.champsoft.vrms.customer.persistence.entity.CustomerJpaEntity;

public class CustomerMapper {

    private CustomerMapper() {
    }

    public static Customer fromRequest(CreateCustomerRequest request) {
        return Customer.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .licenseNumber(request.getLicenseNumber())
                .licenseExpiryDate(request.getLicenseExpiryDate())
                .eligibleToRent(request.getEligibleToRent())
                .build();
    }

    public static Customer fromJpa(CustomerJpaEntity entity) {
        return Customer.builder()
                .customerId(entity.getCustomerId())
                .fullName(entity.getFullName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .licenseNumber(entity.getLicenseNumber())
                .licenseExpiryDate(entity.getLicenseExpiryDate())
                .eligibleToRent(entity.getEligibleToRent())
                .build();
    }

    public static CustomerJpaEntity toJpa(Customer customer) {
        return CustomerJpaEntity.builder()
                .customerId(customer.getCustomerId())
                .fullName(customer.getFullName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .licenseNumber(customer.getLicenseNumber())
                .licenseExpiryDate(customer.getLicenseExpiryDate())
                .eligibleToRent(customer.getEligibleToRent())
                .build();
    }

    public static CustomerResponse toResponse(Customer customer) {
        return CustomerResponse.builder()
                .customerId(customer.getCustomerId())
                .fullName(customer.getFullName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .licenseNumber(customer.getLicenseNumber())
                .licenseExpiryDate(customer.getLicenseExpiryDate())
                .eligibleToRent(customer.getEligibleToRent())
                .build();
    }
}