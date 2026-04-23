package com.champsoft.vrms.rental.infrastructure.acl.http;

import com.champsoft.vrms.billing.api.dto.InvoiceResponse;
import com.champsoft.vrms.billing.domain.model.Invoice;
import com.champsoft.vrms.customer.api.dto.CustomerResponse;
import com.champsoft.vrms.customer.domain.model.Customer;
import com.champsoft.vrms.vehicle.api.dto.VehicleResponse;
import com.champsoft.vrms.vehicle.domain.model.Vehicle;

final class HttpAclMapper {

    private HttpAclMapper() {
    }

    static Customer toDomain(CustomerResponse response) {
        return Customer.builder()
                .customerId(response.getCustomerId())
                .fullName(response.getFullName())
                .email(response.getEmail())
                .phone(response.getPhone())
                .licenseNumber(response.getLicenseNumber())
                .licenseExpiryDate(response.getLicenseExpiryDate())
                .eligibleToRent(response.getEligibleToRent())
                .build();
    }

    static Vehicle toDomain(VehicleResponse response) {
        return Vehicle.builder()
                .vehicleId(response.getVehicleId())
                .brand(response.getBrand())
                .model(response.getModel())
                .modelYear(response.getModelYear())
                .plateNumber(response.getPlateNumber())
                .available(response.getAvailable())
                .conditionStatus(response.getConditionStatus())
                .dailyRate(response.getDailyRate())
                .build();
    }

    static Invoice toDomain(InvoiceResponse response) {
        return Invoice.builder()
                .invoiceId(response.getInvoiceId())
                .rentalId(response.getRentalId())
                .amount(response.getAmount())
                .paymentStatus(response.getPaymentStatus())
                .paymentDate(response.getPaymentDate())
                .build();
    }
}
