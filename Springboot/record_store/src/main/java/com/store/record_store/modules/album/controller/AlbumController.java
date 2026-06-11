package com.store.record_store.modules.album.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import com.store.record_store.modules.Album.dto.AlbumRequestDTO;
import com.store.record_store.modules.Album.dto.AlbumResponseDTO;
import com.store.record_store.modules.Album.Service.AlbumService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/album")
@RequiredArgsConstructor
@Validated
public class AlbumController {
    private final AlbumService service;

    @PostMapping
    public AlbumResponseDTO create(
            @Valid @RequestBody AlbumRequestDTO request) {

        return service.create(request);
    }

    @GetMapping
    public List<AlbumResponseDTO> findAll() {

        return service.findAll();
    }

}
