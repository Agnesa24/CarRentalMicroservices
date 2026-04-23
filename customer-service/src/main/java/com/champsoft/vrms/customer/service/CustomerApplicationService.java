package com.champsoft.vrms.customer.service;

import com.champsoft.vrms.customer.shared.exception.DuplicateResourceException;
import com.champsoft.vrms.customer.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import com.champsoft.vrms.customer.api.dto.CreateCustomerRequest;
import com.champsoft.vrms.customer.api.dto.CustomerResponse;
import com.champsoft.vrms.customer.api.dto.UpdateCustomerRequest;
import com.champsoft.vrms.customer.api.mapper.CustomerMapper;
import com.champsoft.vrms.customer.domain.model.Customer;
import com.champsoft.vrms.customer.domain.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerApplicationService {

    private final CustomerRepository customerRepository;

    public CustomerResponse create(CreateCustomerRequest request) {
        if (customerRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Customer email already exists.");
        }

        Customer customer = CustomerMapper.fromRequest(request);
        customer.validate();

        return CustomerMapper.toResponse(customerRepository.save(customer));
    }

    public List<CustomerResponse> getAll() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerMapper::toResponse)
                .toList();
    }

    public CustomerResponse getById(Long id) {
        return CustomerMapper.toResponse(
                customerRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Customer not found."))
        );
    }

    public Customer getDomainById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found."));
    }

    public CustomerResponse update(Long id, UpdateCustomerRequest request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found."));

        customer.setFullName(request.getFullName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setLicenseNumber(request.getLicenseNumber());
        customer.setLicenseExpiryDate(request.getLicenseExpiryDate());
        customer.setEligibleToRent(request.getEligibleToRent());

        customer.validate();

        return CustomerMapper.toResponse(customerRepository.save(customer));
    }

    public void delete(Long id) {
        customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found."));
        customerRepository.deleteById(id);
    }
}