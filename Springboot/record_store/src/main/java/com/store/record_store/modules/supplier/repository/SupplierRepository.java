package com.store.record_store.modules.supplier.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.store.record_store.modules.supplier.model.Supplier;
import org.springframework.data.jpa.repository.Query;

public interface SupplierRepository extends JpaRepository<Supplier, Long>{

    @Query("""
        SELECT s
        FROM Supplier s
        WHERE s.companyName LIKE %?1%
        """)
    List<Supplier> findByName(String companyName);

}
