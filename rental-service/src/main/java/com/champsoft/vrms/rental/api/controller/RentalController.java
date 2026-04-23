package com.champsoft.vrms.rental.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.champsoft.vrms.billing.api.controller.InvoiceController;
import com.champsoft.vrms.customer.api.controller.CustomerController;
import com.champsoft.vrms.rental.api.dto.CreateRentalRequest;
import com.champsoft.vrms.rental.api.dto.RentalModel;
import com.champsoft.vrms.rental.api.dto.RentalResponse;
import com.champsoft.vrms.rental.api.dto.UpdateRentalRequest;
import com.champsoft.vrms.rental.api.mapper.RentalMapper;
import com.champsoft.vrms.rental.application.service.RentalApplicationService;
import com.champsoft.vrms.vehicle.api.controller.VehicleController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/rentals")
@RequiredArgsConstructor
public class RentalController {
    private final RentalApplicationService service;
    @PostMapping
    public ResponseEntity<RentalModel> create(@Valid @RequestBody CreateRentalRequest request) {
        RentalResponse response = service.create(request);
        return
                ResponseEntity.status(HttpStatus.CREATED).body(addLinks(response));
    }
    @GetMapping
    public ResponseEntity<List<RentalModel>> getAll() {
        return
                ResponseEntity.ok(service.getAll().stream().map(this::addLinks).toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<RentalModel> getById(@PathVariable Long id) {
        return ResponseEntity.ok(addLinks(service.getById(id)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<RentalModel> update(@PathVariable Long id, @Valid
    @RequestBody UpdateRentalRequest request) {
        return ResponseEntity.ok(addLinks(service.update(id, request)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    private RentalModel addLinks(RentalResponse response) {
        RentalModel model = RentalMapper.toModel(response);

        model.add(linkTo(methodOn(RentalController.class).getById(response.getRentalId())).withSelfRel());
        model.add(linkTo(methodOn(RentalController.class).getAll()).withRel("rentals"));
        model.add(linkTo(methodOn(CustomerController.class).getById(response.getCustomerId())).withRel("customer"));
        model.add(linkTo(methodOn(VehicleController.class).getById(response.getVehicleId())).withRel("vehicle"));
        model.add(linkTo(methodOn(InvoiceController.class).getById(response.getInvoiceId())).withRel("invoice"));

        return model;
    }
}
