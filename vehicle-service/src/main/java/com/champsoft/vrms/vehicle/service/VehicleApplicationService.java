package com.champsoft.vrms.vehicle.service;

import com.champsoft.vrms.vehicle.shared.exception.DuplicateResourceException;
import com.champsoft.vrms.vehicle.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import com.champsoft.vrms.vehicle.api.dto.CreateVehicleRequest;
import com.champsoft.vrms.vehicle.api.dto.UpdateVehicleRequest;
import com.champsoft.vrms.vehicle.api.dto.VehicleResponse;
import com.champsoft.vrms.vehicle.api.mapper.VehicleMapper;
import com.champsoft.vrms.vehicle.domain.model.Vehicle;
import com.champsoft.vrms.vehicle.domain.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleApplicationService {
    private final VehicleRepository vehicleRepository;
    public VehicleResponse create(CreateVehicleRequest request) {
        if (vehicleRepository.existsByPlateNumber(request.getPlateNumber())) {
            throw new DuplicateResourceException("Vehicle plate number already exists.");
        }
        Vehicle vehicle = VehicleMapper.toDomain(request);
        vehicle.validate();
        return VehicleMapper.toResponse(vehicleRepository.save(vehicle));
    }
    public List<VehicleResponse> getAll() {
        return
                vehicleRepository.findAll().stream().map(VehicleMapper::toResponse).toList();
    }

    public VehicleResponse getById(Long id) {
        return VehicleMapper.toResponse(getDomainById(id));
    }
    public Vehicle getDomainById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found."));
    }
    public VehicleResponse update(Long id, UpdateVehicleRequest request) {
        Vehicle vehicle = getDomainById(id);
        vehicle.setBrand(request.getBrand());
        vehicle.setModel(request.getModel());
        vehicle.setModelYear(request.getModelYear());
        vehicle.setPlateNumber(request.getPlateNumber());
        vehicle.setAvailable(request.getAvailable());
        vehicle.setConditionStatus(request.getConditionStatus());
        vehicle.setDailyRate(request.getDailyRate());
        vehicle.validate();
        return VehicleMapper.toResponse(vehicleRepository.save(vehicle));
    }
    public void delete(Long id) {
        getById(id);
        vehicleRepository.deleteById(id);
    }
    public void markUnavailable(Long id) {
        Vehicle vehicle = getDomainById(id);
        vehicle.markUnavailable();
        vehicleRepository.save(vehicle);
    }

    public void markAvailable(Long id) {
        Vehicle vehicle = getDomainById(id);
        vehicle.setAvailable(true);
        vehicleRepository.save(vehicle);
    }
}
