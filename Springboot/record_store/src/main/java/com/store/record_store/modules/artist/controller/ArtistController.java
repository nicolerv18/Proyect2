package com.store.record_store.modules.artist.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.store.record_store.modules.artist.dto.ArtistRequestDto;
import com.store.record_store.modules.artist.dto.ArtistResponseDto;
import com.store.record_store.modules.artist.services.IArtistService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/artist")
public class ArtistController {

    private final IArtistService service;

    @PostMapping
    public ResponseEntity<ArtistResponseDto> create(@Validated @RequestBody ArtistRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(requestDto));
    }

    @GetMapping
    public ResponseEntity<List<ArtistResponseDto>> getAll(@RequestParam(defaultValue = "") String filter) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findAll(filter));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findById(id));
    }
}
