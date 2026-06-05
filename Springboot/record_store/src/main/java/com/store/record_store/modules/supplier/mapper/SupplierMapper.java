package com.store.record_store.modules.supplier.mapper;


import org.mapstruct.Mapper;

import com.store.record_store.modules.supplier.dto.request.SupplierRequestDTO;
import com.store.record_store.modules.supplier.dto.response.SupplierResponseDTO;
import com.store.record_store.modules.supplier.model.Supplier;


@Mapper(componentModel = "string")
public interface SupplierMapper {
    
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updateAt", ingore = true)

    Supplier toEntity(SupplierRequestDTO request0);
    SupplierRequestDTO toResponse (Supplier supplier)

    List<SupplierResponseDTO> toEntityList(List<Supplier> supplier);
}
