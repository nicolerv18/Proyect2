package com.store.record_store.modules.supplier.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import com.store.record_store.modules.supplier.dto.request.SupplierRequestDTO;
import com.store.record_store.modules.supplier.dto.response.SupplierResponseDTO;
import com.store.record_store.modules.supplier.service.SupplierService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/supplier")
@RequiredArgsConstructor
@Validated
public class SupplierController {

    private final SupplierService service;

    @PostMapping
    public SupplierResponseDTO create(
            @Valid @RequestBody SupplierRequestDTO request) {

        return service.create(request);
    }

    @GetMapping
    public List<SupplierResponseDTO> findAll() {

        return service.findAll();
    }
}