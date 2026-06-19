package com.store.record_store.modules.artist.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.record_store.modules.artist.dto.ArtistRequestDto;
import com.store.record_store.modules.artist.dto.ArtistResponseDto;
import com.store.record_store.modules.artist.mapper.ArtistMapper;
import com.store.record_store.modules.artist.model.Artist;
import com.store.record_store.modules.artist.repository.ArtistRepository;
import com.store.record_store.modules.artist.services.IArtistService;

@Service
public class ArtistService implements IArtistService {

    @Autowired
    public ArtistRepository data;
    
    @Autowired
    public ArtistMapper mapper;

    @Override
    public ArtistResponseDto create(ArtistRequestDto requestDto) {
        Artist artist = mapper.toEntity(requestDto);
        Artist saved = data.save(artist);
        return mapper.toDto(saved);
    }

    @Override
    public List<ArtistResponseDto> findAll(String filter) {
        return mapper.toDtoList(data.findByNameContaining(filter));
    }

    @Override
    public ArtistResponseDto findById(Long id) {
        var artist = data.findById(id);
        if (artist.isEmpty())
            return null;
        return mapper.toDto(artist.get());
    }
}
