package com.store.record_store.modules.branch.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.store.record_store.modules.branch.model.Branch;

public interface BranchRepository extends JpaRepository<Branch, UUID> {

    boolean existsByName(String name);

    @Query("""
        SELECT b
        FROM Branch b
        WHERE
        b.name LIKE %?1% OR
        b.city LIKE %?1% OR
        b.address LIKE %?1%
        """)
    List<Branch> findAll(String filter);
}
