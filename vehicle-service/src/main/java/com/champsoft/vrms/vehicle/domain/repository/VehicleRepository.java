package com.champsoft.vrms.vehicle.domain.repository;

import com.champsoft.vrms.vehicle.domain.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository {
    Vehicle save(Vehicle vehicle);
    Optional<Vehicle> findById(Long id);
    List<Vehicle> findAll();
    void deleteById(Long id);
    boolean existsByPlateNumber(String plateNumber);
}