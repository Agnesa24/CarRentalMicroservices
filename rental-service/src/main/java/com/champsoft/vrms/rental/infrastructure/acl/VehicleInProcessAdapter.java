package com.champsoft.vrms.rental.infrastructure.acl;

import lombok.RequiredArgsConstructor;
import com.champsoft.vrms.rental.application.port.out.VehiclePort;
import com.champsoft.vrms.vehicle.domain.model.Vehicle;
import com.champsoft.vrms.vehicle.service.VehicleApplicationService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "rental.integration", name = "mode", havingValue = "in-process", matchIfMissing = false)
public class VehicleInProcessAdapter implements VehiclePort {

    private final VehicleApplicationService vehicleService;

    @Override
    public Vehicle getById(Long vehicleId) {
        return vehicleService.getDomainById(vehicleId);
    }

    @Override
    public void markUnavailable(Long vehicleId) {
        vehicleService.markUnavailable(vehicleId);
    }

    @Override
    public void markAvailable(Long vehicleId) {
        vehicleService.markAvailable(vehicleId);
    }
}
