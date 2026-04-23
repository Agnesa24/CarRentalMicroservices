package com.champsoft.vrms.rental.application.port.out;

import com.champsoft.vrms.customer.domain.model.Customer;

public interface CustomerPort {
    Customer getById(Long customerId);
}
