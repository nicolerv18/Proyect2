package com.store.record_store.shared.repository;

import com.store.record_store.shared.ABaseEntity.ABaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface ABaseRepository<T extends ABaseEntity, ID> 
        extends JpaRepository<T, ID> {
}