package com.champsoft.vrms.billing.domain.repository;

import com.champsoft.vrms.billing.domain.model.Invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository {
    Invoice save(Invoice invoice);
    Optional<Invoice> findById(Long id);
    List<Invoice> findAll();
    void deleteById(Long id);
}
