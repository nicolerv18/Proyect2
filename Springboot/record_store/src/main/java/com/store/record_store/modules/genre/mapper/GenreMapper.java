package com.store.record_store.modules.genre.mapper;
import com.store.record_store.modules.genre.dto.GenreRequest;
import com.store.record_store.modules.genre.dto.GenreResponse;
import com.store.record_store.modules.genre.model.genre;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


import java.util.List;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface GenreMapper {

    @Mapping(target="id", ignore= true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    genre toEntity(GenreRequest dto);

    GenreResponse toResponse( genre entity);

    List<GenreResponse> toResponseList(List<genre> entinties);

    @Mapping( target = "id", ignore = true)
    @Mapping( target = "createdAt", ignore = true)
    @Mapping( target = "updatedAt", ignore = true )
    void updateEntity(@MappingTarget  genre entity, GenreRequest dto);



    
} 