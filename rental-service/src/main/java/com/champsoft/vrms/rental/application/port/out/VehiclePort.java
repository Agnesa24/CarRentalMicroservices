package com.champsoft.vrms.rental.application.port.out;

import com.champsoft.vrms.vehicle.domain.model.Vehicle;

public interface VehiclePort {
    Vehicle getById(Long vehicleId);

    void markUnavailable(Long vehicleId);

    void markAvailable(Long vehicleId);
}
