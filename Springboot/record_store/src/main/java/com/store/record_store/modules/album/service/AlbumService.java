package com.store.record_store.modules.album.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.store.record_store.modules.album.dto.AlbumRequestDTO;
import com.store.record_store.modules.album.dto.AlbumResponseDTO;
import com.store.record_store.modules.album.mapper.AlbumMapper;
import com.store.record_store.modules.album.model.Album;
import com.store.record_store.modules.album.repository.AlbumRepository;
import com.store.record_store.modules.supplier.model.Supplier;
import com.store.record_store.modules.supplier.repository.SupplierRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepository repository;
    private final AlbumMapper mapper;
    private final SupplierRepository supplierRepository;
   public AlbumResponseDTO create(AlbumRequestDTO request) {

    Supplier supplier = supplierRepository.findById(request.getSupplierId())
            .orElseThrow(() -> new RuntimeException("Supplier not found"));

    Album album = mapper.toEntity(request);

    album.setSupplier(supplier);
    album.setStatus("AVAILABLE");

    album = repository.save(album);

    return mapper.toResponse(album);
}

    public List<AlbumResponseDTO> findAll(){
        List<Album> albums = repository.findAll();
        return mapper.toResponseList(albums);   
    }
}
