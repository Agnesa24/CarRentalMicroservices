package com.champsoft.vrms.rental.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import com.champsoft.vrms.rental.api.mapper.RentalMapper;
import com.champsoft.vrms.rental.domain.model.Rental;
import com.champsoft.vrms.rental.domain.repository.RentalRepository;
import com.champsoft.vrms.rental.infrastructure.persistence.repository.SpringDataRentalRepository;
import com.champsoft.vrms.vehicle.api.mapper.VehicleMapper;
import com.champsoft.vrms.vehicle.domain.model.Vehicle;
import com.champsoft.vrms.vehicle.domain.repository.VehicleRepository;
import com.champsoft.vrms.vehicle.persistence.entity.VehicleJpaEntity;
import com.champsoft.vrms.vehicle.persistence.repository.SpringDataVehicleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class RentalRepositoryAdapter implements RentalRepository {
    private final SpringDataRentalRepository repository;
    @Override
    public Rental save(Rental rental) {
        return
                RentalMapper.toDomain(repository.save(RentalMapper.toJpa(rental)));
    }
    @Override
    public Optional<Rental> findById(Long id) {
        return repository.findById(id).map(RentalMapper::toDomain);
    }
    @Override
    public List<Rental> findAll() {
        return
                repository.findAll().stream().map(RentalMapper::toDomain).toList();
    }
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}