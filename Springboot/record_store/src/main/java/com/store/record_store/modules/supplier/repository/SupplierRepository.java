package com.store.record_store.modules.supplier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.store.record_store.modules.supplier.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long>{

    @Query("""
        SELECT s
        FROM Supplier s
        WHERE s.companyName LIKE %?1%
        """)
    List<Supplier> findByName(String companyName);

}
