package com.champsoft.vrms.rental.infrastructure.acl.http;

import lombok.RequiredArgsConstructor;
import com.champsoft.vrms.rental.application.port.out.VehiclePort;
import com.champsoft.vrms.rental.shared.exception.BusinessRuleException;
import com.champsoft.vrms.rental.shared.exception.ResourceNotFoundException;
import com.champsoft.vrms.vehicle.api.dto.UpdateVehicleRequest;
import com.champsoft.vrms.vehicle.api.dto.VehicleResponse;
import com.champsoft.vrms.vehicle.domain.model.Vehicle;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "rental.integration", name = "mode", havingValue = "http", matchIfMissing = true)
public class VehicleHttpAdapter implements VehiclePort {

    private final RestTemplate restTemplate;
    private final RentalHttpProperties properties;

    @Override
    public Vehicle getById(Long vehicleId) {
        String url = properties.getVehicleBaseUrl() + "/" + vehicleId;
        ResponseEntity<VehicleResponse> response = restTemplate.getForEntity(url, VehicleResponse.class);
        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new ResourceNotFoundException("Vehicle not found.");
        }
        return HttpAclMapper.toDomain(response.getBody());
    }

    @Override
    public void markUnavailable(Long vehicleId) {
        Vehicle vehicle = getById(vehicleId);
        updateAvailability(vehicle, false);
    }

    @Override
    public void markAvailable(Long vehicleId) {
        Vehicle vehicle = getById(vehicleId);
        updateAvailability(vehicle, true);
    }

    private void updateAvailability(Vehicle vehicle, boolean available) {
        String url = properties.getVehicleBaseUrl() + "/" + vehicle.getVehicleId();
        UpdateVehicleRequest request = new UpdateVehicleRequest();
        request.setBrand(vehicle.getBrand());
        request.setModel(vehicle.getModel());
        request.setModelYear(vehicle.getModelYear());
        request.setPlateNumber(vehicle.getPlateNumber());
        request.setAvailable(available);
        request.setConditionStatus(vehicle.getConditionStatus());
        request.setDailyRate(vehicle.getDailyRate());

        ResponseEntity<VehicleResponse> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(request),
                VehicleResponse.class
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new BusinessRuleException("Failed to update vehicle availability.");
        }
    }
}
