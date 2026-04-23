package com.champsoft.vrms.billing.persistence.repository;

import com.champsoft.vrms.billing.persistence.entity.InvoiceJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataInvoiceRepository extends
        JpaRepository<InvoiceJpaEntity, Long> {
}
