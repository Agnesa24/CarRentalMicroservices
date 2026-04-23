package com.champsoft.vrms.vehicle.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.champsoft.vrms.vehicle.api.dto.CreateVehicleRequest;
import com.champsoft.vrms.vehicle.api.dto.UpdateVehicleRequest;
import com.champsoft.vrms.vehicle.api.dto.VehicleResponse;
import com.champsoft.vrms.vehicle.service.VehicleApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleApplicationService service;
    @PostMapping
    public ResponseEntity<VehicleResponse> create(@Valid @RequestBody CreateVehicleRequest request) {
        return
                ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }
    @GetMapping
    public ResponseEntity<List<VehicleResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<VehicleResponse> update(@PathVariable Long id, @Valid
    @RequestBody UpdateVehicleRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
