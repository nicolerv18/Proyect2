package com.store.record_store.modules.artist.services;

import java.util.List;
import com.store.record_store.modules.artist.dto.ArtistRequestDto;
import com.store.record_store.modules.artist.dto.ArtistResponseDto;

public interface IArtistService {
    public ArtistResponseDto create(ArtistRequestDto requestDto);
    public List<ArtistResponseDto> findAll(String filter);
    public ArtistResponseDto findById(Long id);
}