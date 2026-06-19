package com.store.record_store.modules.artist.controller;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.store.record_store.modules.artist.dto.ArtistRequestDto;
import com.store.record_store.modules.artist.dto.ArtistResponseDto;
import com.store.record_store.modules.artist.services.IArtistService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/artist")
public class ArtistController {

    private final IArtistService service;

    @PostMapping
    public ResponseEntity<ArtistResponseDto> create(
            @Valid @RequestBody ArtistRequestDto requestDto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(requestDto));
    }

    @GetMapping
    public ResponseEntity<List<ArtistResponseDto>> getAll(
            @RequestParam(defaultValue = "") String filter) {

        return ResponseEntity.ok(service.findAll(filter));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistResponseDto> getById(
            @PathVariable UUID id) {

        return ResponseEntity.ok(service.findById(id));
    }
}