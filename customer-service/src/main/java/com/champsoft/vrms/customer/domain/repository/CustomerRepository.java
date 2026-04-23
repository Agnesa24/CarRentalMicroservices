package com.champsoft.vrms.customer.domain.repository;

import com.champsoft.vrms.customer.domain.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Customer save(Customer customer);
    Optional<Customer> findById(Long id);
    List<Customer> findAll();
    void deleteById(Long id);
    boolean existsByEmail(String email);
}
