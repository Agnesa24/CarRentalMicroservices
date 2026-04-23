package com.champsoft.vrms.customer.persistence.repository;

import com.champsoft.vrms.customer.persistence.entity.CustomerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCustomerRepository extends JpaRepository<CustomerJpaEntity, Long> {
    boolean existsByEmail(String email);
}