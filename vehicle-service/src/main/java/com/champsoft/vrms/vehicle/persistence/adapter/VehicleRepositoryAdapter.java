package com.champsoft.vrms.vehicle.persistence.adapter;

import lombok.RequiredArgsConstructor;
import com.champsoft.vrms.vehicle.api.mapper.VehicleMapper;
import com.champsoft.vrms.vehicle.domain.model.Vehicle;
import com.champsoft.vrms.vehicle.domain.repository.VehicleRepository;
import com.champsoft.vrms.vehicle.persistence.repository.SpringDataVehicleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VehicleRepositoryAdapter implements VehicleRepository {
    private final SpringDataVehicleRepository repository;
    @Override
    public Vehicle save(Vehicle vehicle) {
        return VehicleMapper.toDomain(repository.save(VehicleMapper.toJpa(vehicle)));
    }
    @Override
    public Optional<Vehicle> findById(Long id) {
        return repository.findById(id).map(VehicleMapper::toDomain);
    }
    @Override
    public List<Vehicle> findAll() {
        return repository.findAll().stream().map(VehicleMapper::toDomain).toList();
    }
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    @Override
    public boolean existsByPlateNumber(String plateNumber) {
        return repository.existsByPlateNumber(plateNumber);
    }
}
