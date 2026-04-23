package com.champsoft.vrms.vehicle.domain.model;

import com.champsoft.vrms.vehicle.shared.exception.BusinessRuleException;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {
    private Long vehicleId;
    private String brand;
    private String model;
    private Integer modelYear;
    private String plateNumber;
    private Boolean available;
    private String conditionStatus;
    private Double dailyRate;
    public void validate() {
        if (dailyRate == null || dailyRate <= 0) {
            throw new BusinessRuleException("Daily rate must be greater than 0.");
        }
    }
    public void markUnavailable() {
        if (!Boolean.TRUE.equals(available)) {
            throw new BusinessRuleException("Vehicle is already unavailable.");
        }
        this.available = false;
    }
}