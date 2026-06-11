package com.store.record_store.modules.supplier.service;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.store.record_store.modules.supplier.dto.SupplierRequestDTO;
import com.store.record_store.modules.supplier.dto.SupplierResponseDTO;
import com.store.record_store.modules.supplier.mapper.SupplierMapper;
import com.store.record_store.modules.supplier.model.Supplier;
import com.store.record_store.modules.supplier.repository.SupplierRepository;

@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepository repository;
    private final SupplierMapper mapper;

    public SupplierResponseDTO create(SupplierRequestDTO request) {
        Supplier supplier = mapper.toEntity(request);
        supplier = repository.save(supplier);
        return mapper.toResponse(supplier);
    }

    public List<SupplierResponseDTO> findAll(){
        List<Supplier> suppliers = repository.findAll();
        return mapper.toEntityList(suppliers);
    }
}