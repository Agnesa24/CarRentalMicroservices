package com.champsoft.vrms.vehicle.api.mapper;

import com.champsoft.vrms.vehicle.api.dto.CreateVehicleRequest;
import com.champsoft.vrms.vehicle.api.dto.VehicleResponse;
import com.champsoft.vrms.vehicle.domain.model.Vehicle;
import com.champsoft.vrms.vehicle.persistence.entity.VehicleJpaEntity;

public class VehicleMapper {
    private VehicleMapper() {}
    public static Vehicle toDomain(CreateVehicleRequest request) {
        return Vehicle.builder()
                .brand(request.getBrand())
                .model(request.getModel())
                .modelYear(request.getModelYear())
                .plateNumber(request.getPlateNumber())
                .available(request.getAvailable())
                .conditionStatus(request.getConditionStatus())
                .dailyRate(request.getDailyRate())
                .build();
    }
    public static Vehicle toDomain(VehicleJpaEntity entity) {
        return Vehicle.builder()
                .vehicleId(entity.getVehicleId())
                .brand(entity.getBrand())
                .model(entity.getModel())
                .modelYear(entity.getModelYear())
                .plateNumber(entity.getPlateNumber())
                .available(entity.getAvailable())
                .conditionStatus(entity.getConditionStatus())
                .dailyRate(entity.getDailyRate())
                .build();
    }
    public static VehicleJpaEntity toJpa(Vehicle vehicle) {
        return VehicleJpaEntity.builder()
                .vehicleId(vehicle.getVehicleId())
                .brand(vehicle.getBrand())
                .model(vehicle.getModel())
                .modelYear(vehicle.getModelYear())
                .plateNumber(vehicle.getPlateNumber())
                .available(vehicle.getAvailable())
                .conditionStatus(vehicle.getConditionStatus())
                .dailyRate(vehicle.getDailyRate())
                .build();
    }
    public static VehicleResponse toResponse(Vehicle vehicle) {
        return VehicleResponse.builder()
                .vehicleId(vehicle.getVehicleId())
                .brand(vehicle.getBrand())
                .model(vehicle.getModel())
                .modelYear(vehicle.getModelYear())
                .plateNumber(vehicle.getPlateNumber())
                .available(vehicle.getAvailable())
                .conditionStatus(vehicle.getConditionStatus())
                .dailyRate(vehicle.getDailyRate())
                .build();
    }
}
