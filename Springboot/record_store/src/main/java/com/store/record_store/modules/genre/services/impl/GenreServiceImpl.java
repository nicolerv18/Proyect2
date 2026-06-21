package com.store.record_store.modules.genre.services.impl;

import com.store.record_store.modules.genre.dto.GenreRequest;
import com.store.record_store.modules.genre.dto.GenreResponse;
import com.store.record_store.modules.genre.mapper.GenreMapper;
import com.store.record_store.modules.genre.model.genre;
import com.store.record_store.modules.genre.repository.GenreRepository;
import com.store.record_store.modules.genre.services.GenreService;
import com.store.record_store.shared.service.impl.ABaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GenreServiceImpl
        extends ABaseServiceImpl<
                genre,
                UUID,
                GenreRequest,
                GenreResponse,
                GenreRepository>
        implements GenreService {

    private final GenreMapper genreMapper;

    public GenreServiceImpl(GenreMapper genreMapper) {
        this.genreMapper = genreMapper;
    }

    @Override
    protected GenreResponse toResponse(genre entity) {
        return genreMapper.toResponse(entity);
    }

    @Override
    protected genre toEntity(GenreRequest request) {
        return genreMapper.toEntity(request);
    }
}