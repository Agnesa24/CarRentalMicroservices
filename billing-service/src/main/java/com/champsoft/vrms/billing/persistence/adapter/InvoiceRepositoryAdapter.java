package com.champsoft.vrms.billing.persistence.adapter;

import lombok.RequiredArgsConstructor;
import com.champsoft.vrms.billing.api.mapper.InvoiceMapper;
import com.champsoft.vrms.billing.domain.model.Invoice;
import com.champsoft.vrms.billing.domain.repository.InvoiceRepository;
import com.champsoft.vrms.billing.persistence.repository.SpringDataInvoiceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class InvoiceRepositoryAdapter implements InvoiceRepository {
    private final SpringDataInvoiceRepository repository;
    @Override
    public Invoice save(Invoice invoice) {
        return InvoiceMapper.toDomain(repository.save(InvoiceMapper.toJpa(invoice)));
    }
    @Override
    public Optional<Invoice> findById(Long id) {
        return repository.findById(id).map(InvoiceMapper::toDomain);
    }
    @Override
    public List<Invoice> findAll() {
        return
                repository.findAll().stream().map(InvoiceMapper::toDomain).toList();
    }
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
