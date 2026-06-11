package com.store.record_store.modules.Album.Service;


import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.store.record_store.modules.Album.repository.AlbumRepository;
import com.store.record_store.modules.Album.dto.AlbumRequestDTO;
import com.store.record_store.modules.Album.dto.AlbumResponseDTO;
import com.store.record_store.modules.Album.Mapper.AlbumMapper;
import com.store.record_store.modules.Album.model.Album;

@Service
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepository repository;
    private final AlbumMapper mapper;

    public AlbumResponseDTO create(AlbumRequestDTO request) {
        Album album = mapper.toEntity(request);
        album = repository.save(album);
        return mapper.toResponse(album);
    }

    public List<AlbumResponseDTO> findAll(){
        List<Album> albums = repository.findAll();
        return mapper.toEntityList(albums);
    }
}
