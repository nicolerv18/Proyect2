package com.store.record_store.modules.genre.controller;

import com.store.record_store.modules.genre.dto.*;
import com.store.record_store.modules.genre.services.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/genre")
@RequiredArgsConstructor
@Tag (name= "genre" , description= "API gestion de usuarios")
public class GenreController {
    
    private final GenreService genreService;
    @PostMapping
    @Operation( summary = "Crear un nuevo genero")
    public ResponseEntity<GenreResponse> created(@Valid @RequestBody GenreRequest request){
        GenreResponse response = genreService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation( summary = "Obtener genero por id")
    public ResponseEntity<GenreResponse> findById(@PathVariable UUID id){
        GenreResponse response = genreService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/page")
    @Operation(summary = "Listar genero con paginacion")
    public ResponseEntity<Page<GenreResponse>> findAll(Pageable pageable){
        Page<GenreResponse> response = genreService.findAll(pageable);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    @Operation( summary = "Actualizar genero")
    public ResponseEntity<GenreResponse> update( @PathVariable UUID id, @Valid @RequestBody GenreRequest request){
        GenreResponse response= genreService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation( summary = "Eliminar genero")
    public ResponseEntity<Void> delete( @PathVariable UUID id){
        genreService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
