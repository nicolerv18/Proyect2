package com.store.record_store.modules.artist.services.abst;

import com.store.record_store.modules.artist.dto.ArtistRequestDto;
import com.store.record_store.modules.artist.dto.ArtistResponseDto;
import com.store.record_store.modules.artist.repository.ArtistRepository;
import com.store.record_store.modules.artist.services.IArtistService;

public abstract class AAbstract implements IArtistService {

    protected final ArtistRepository repository;

    protected AAbstract(ArtistRepository repository) {
        this.repository = repository;
    }

    @Override
    public abstract ArtistResponseDto create(ArtistRequestDto requestDto);
}
