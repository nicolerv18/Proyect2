package com.store.record_store.shared.service.impl;

import com.store.record_store.shared.ABaseEntity.ABaseEntity;
import com.store.record_store.shared.repository.ABaseRepository;
import com.store.record_store.shared.service.ABaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public abstract class ABaseServiceImpl<
        T extends ABaseEntity, 
        ID extends UUID,  
        REQ, 
        RES, 
        R extends ABaseRepository<T, ID>>
        implements ABaseService<T, ID, REQ, RES> {

    @Autowired
    protected R repository;

    protected abstract RES toResponse(T entity);
    protected abstract T toEntity(REQ request);

    @Override
    public RES create(REQ request) {
        T entity = toEntity(request);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        T saved = repository.save(entity);
        return toResponse(saved);
    }

    @Override
    public RES update(ID id, REQ request) {
        T existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entity not found with id: " + id));

        T entity = toEntity(request);
        entity.setId(id);
        entity.setCreatedAt(existing.getCreatedAt());
        entity.setUpdatedAt(LocalDateTime.now());

        T saved = repository.save(entity);
        return toResponse(saved);
    }

    @Override
    public RES findById(ID id) {
        T entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entity not found with id: " + id));
        return toResponse(entity);
    }

    @Override
    public List<RES> findAll() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public Page<RES> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this::toResponse);
    }

    @Override
    public void delete(ID id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(ID id) {
        return repository.existsById(id);
    }
}
