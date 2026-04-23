package com.champsoft.vrms.vehicle.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VehicleResponse {
    private Long vehicleId;
    private String brand;
    private String model;
    private Integer modelYear;
    private String plateNumber;
    private Boolean available;
    private String conditionStatus;
    private Double dailyRate;
}

