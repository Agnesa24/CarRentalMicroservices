package com.champsoft.vrms.vehicle.persistence.repository;

import com.champsoft.vrms.vehicle.persistence.entity.VehicleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataVehicleRepository extends
        JpaRepository<VehicleJpaEntity, Long> {
    boolean existsByPlateNumber(String plateNumber);
}
