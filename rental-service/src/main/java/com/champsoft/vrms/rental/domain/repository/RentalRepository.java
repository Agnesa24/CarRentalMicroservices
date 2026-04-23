package com.champsoft.vrms.rental.domain.repository;

import com.champsoft.vrms.rental.domain.model.Rental;

import java.util.List;
import java.util.Optional;

public interface RentalRepository {
    Rental save(Rental rental);
    Optional<Rental> findById(Long id);
    List<Rental> findAll();
    void deleteById(Long id);
}
