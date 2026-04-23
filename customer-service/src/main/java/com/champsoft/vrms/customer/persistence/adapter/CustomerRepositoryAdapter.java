package com.champsoft.vrms.customer.persistence.adapter;

import lombok.RequiredArgsConstructor;
import com.champsoft.vrms.customer.api.mapper.CustomerMapper;
import com.champsoft.vrms.customer.domain.model.Customer;
import com.champsoft.vrms.customer.domain.repository.CustomerRepository;
import com.champsoft.vrms.customer.persistence.entity.CustomerJpaEntity;
import com.champsoft.vrms.customer.persistence.repository.SpringDataCustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryAdapter implements CustomerRepository {

    private final SpringDataCustomerRepository repository;

    @Override
    public Customer save(Customer customer) {
        CustomerJpaEntity entity = repository.save(CustomerMapper.toJpa(customer));
        return CustomerMapper.fromJpa(entity);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return repository.findById(id).map(CustomerMapper::fromJpa);
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll().stream()
                .map(CustomerMapper::fromJpa)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
}