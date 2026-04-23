package com.champsoft.vrms.rental.infrastructure.persistence.repository;


import com.champsoft.vrms.rental.infrastructure.persistence.entity.RentalJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataRentalRepository extends
        JpaRepository<RentalJpaEntity, Long> {
}