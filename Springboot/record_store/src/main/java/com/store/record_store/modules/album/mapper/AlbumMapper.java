package com.store.record_store.modules.album.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.store.record_store.modules.album.dto.AlbumRequestDTO;
import com.store.record_store.modules.album.dto.AlbumResponseDTO;
import com.store.record_store.modules.album.model.Album;

@Mapper(componentModel = "spring")
public interface AlbumMapper {
     @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "supplier", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Album toEntity(AlbumRequestDTO request);

    AlbumResponseDTO toResponse(Album album);

    List<AlbumResponseDTO> toResponseList(List<Album> album);
}
