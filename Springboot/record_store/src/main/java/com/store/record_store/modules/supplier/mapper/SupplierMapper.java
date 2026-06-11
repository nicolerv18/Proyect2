package com.store.record_store.modules.supplier.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.store.record_store.modules.supplier.dto.SupplierRequestDTO;
import com.store.record_store.modules.supplier.dto.SupplierResponseDTO;
import com.store.record_store.modules.supplier.model.Supplier;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Supplier toEntity(SupplierRequestDTO request);
    
    SupplierResponseDTO toResponse(Supplier supplier);
    
    List<SupplierResponseDTO> toEntityList(List<Supplier> supplier);
}
