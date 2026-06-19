package com.store.record_store.shared.service;

import com.store.record_store.shared.ABaseEntity.ABaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ABaseService<T extends ABaseEntity, ID, REQ, RES> {

    RES create(REQ request);
    RES update(ID id, REQ request);
    RES findById(ID id);
    List<RES> findAll();
    Page<RES> findAll(Pageable pageable);
    void delete(ID id);
    boolean existsById(ID id);
}