package com.store.record_store.modules.supplier.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.store.record_store.modules.supplier.model.Supplier;
import org.springframework.data.jpa.repository.Query;

public interface SupplierRepository extends JpaRepository<Supplier, Long>{

    @Query("""
        SELECT S
        FROM Supplier s
        WHERE
        s.companyName like %?1%
        """;)

        List<supplier> findByName (String companyName)

}
