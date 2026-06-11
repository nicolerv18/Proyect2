package com.store.record_store.modules.Album.Mapper;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.store.record_store.modules.supplier.dto.SupplierRequestDTO;
import com.store.record_store.modules.supplier.dto.SupplierResponseDTO;
import com.store.record_store.modules.supplier.model.Supplier;

@Mapper(componentModel = "spring")
public interface AlbumMapper {
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Album toEntity(AlbumRequestDTO request);

    AlbumResponseDTO toResponse(Album album);

    List<AlbumResponseDTO> toEntityList(List<Album> album);
}
