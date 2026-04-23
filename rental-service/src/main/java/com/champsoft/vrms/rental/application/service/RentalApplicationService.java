package com.champsoft.vrms.rental.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import com.champsoft.vrms.billing.domain.model.Invoice;
import com.champsoft.vrms.customer.domain.model.Customer;
import com.champsoft.vrms.rental.api.dto.CreateRentalRequest;
import com.champsoft.vrms.rental.api.dto.RentalResponse;
import com.champsoft.vrms.rental.api.dto.UpdateRentalRequest;
import com.champsoft.vrms.rental.api.mapper.RentalMapper;
import com.champsoft.vrms.rental.application.port.out.BillingPort;
import com.champsoft.vrms.rental.application.port.out.CustomerPort;
import com.champsoft.vrms.rental.application.port.out.VehiclePort;
import com.champsoft.vrms.rental.domain.model.Rental;
import com.champsoft.vrms.rental.domain.repository.RentalRepository;
import com.champsoft.vrms.rental.shared.exception.BusinessRuleException;
import com.champsoft.vrms.rental.shared.exception.ResourceNotFoundException;
import com.champsoft.vrms.vehicle.domain.model.Vehicle;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalApplicationService {

    private final RentalRepository rentalRepository;
    private final CustomerPort customerPort;
    private final VehiclePort vehiclePort;
    private final BillingPort billingPort;

    @Transactional
    public RentalResponse create(CreateRentalRequest request) {
        Customer customer = customerPort.getById(request.getCustomerId());
        if (!Boolean.TRUE.equals(customer.getEligibleToRent())) {
            throw new BusinessRuleException("Customer is not eligible to rent.");
        }

        Vehicle vehicle = vehiclePort.getById(request.getVehicleId());
        if (!Boolean.TRUE.equals(vehicle.getAvailable())
                || !"RENTABLE".equalsIgnoreCase(vehicle.getConditionStatus())) {
            throw new BusinessRuleException("Vehicle is not available for rental.");
        }

        Rental rental = RentalMapper.toDomain(request);
        rental.validate();

        long days = ChronoUnit.DAYS.between(rental.getStartDate(), rental.getEndDate());
        BigDecimal amount = BigDecimal.valueOf(vehicle.getDailyRate()).multiply(BigDecimal.valueOf(days));
        Invoice invoice = billingPort.createFromAmount(amount);
        rental.setInvoiceId(invoice.getInvoiceId());

        Rental saved = rentalRepository.save(rental);
        vehiclePort.markUnavailable(vehicle.getVehicleId());

        vehicle.setAvailable(false);
        return RentalMapper.toResponse(saved, customer, vehicle, invoice);
    }

    public List<RentalResponse> getAll() {
        return rentalRepository.findAll().stream().map(this::mapWithAggregates).toList();
    }

    public RentalResponse getById(Long id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rental not found."));
        return mapWithAggregates(rental);
    }

    public RentalResponse update(Long id, UpdateRentalRequest request) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rental not found."));
        rental.setStatus(request.getStatus());
        return mapWithAggregates(rentalRepository.save(rental));
    }

    @Transactional
    public void delete(Long id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rental not found."));

        if ("CREATED".equalsIgnoreCase(rental.getStatus())
                || "ACTIVE".equalsIgnoreCase(rental.getStatus())) {
            vehiclePort.markAvailable(rental.getVehicleId());
        }

        rentalRepository.deleteById(id);
    }

    private RentalResponse mapWithAggregates(Rental rental) {
        Customer customer = customerPort.getById(rental.getCustomerId());
        Vehicle vehicle = vehiclePort.getById(rental.getVehicleId());
        Invoice invoice = billingPort.getById(rental.getInvoiceId());
        return RentalMapper.toResponse(rental, customer, vehicle, invoice);
    }
}