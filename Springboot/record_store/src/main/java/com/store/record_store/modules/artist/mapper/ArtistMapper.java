package com.store.record_store.modules.artist.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.store.record_store.modules.artist.dto.ArtistRequestDto;
import com.store.record_store.modules.artist.dto.ArtistResponseDto;
import com.store.record_store.modules.artist.model.Artist;

@Mapper(componentModel = "spring")
public interface ArtistMapper {

    @Mapping(target = "artistId", ignore = true)
    @Mapping(target = "country", ignore = true)
    Artist toEntity(ArtistRequestDto requestDto);

    @Mapping(source = "country.name", target = "countryName")
    ArtistResponseDto toDto(Artist artist);

    List<ArtistResponseDto> toDtoList(List<Artist> artists);
}
