package com.store.record_store.modules.branch.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.store.record_store.modules.branch.dto.BranchRequestDto;
import com.store.record_store.modules.branch.dto.BranchResponseDto;
import com.store.record_store.modules.branch.services.IBranchService;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@RestController
@RequestMapping("/branch")
public class BranchController {

    private final IbranchService service;

    @PostMapping
    public ResponseEntity<BranchResponseDto> create(@Validated @RequestBody BranchRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(requestDto));
    }

    @GetMapping
    public ResponseEntity<List<BranchResponseDto>> getAll(@RequestParam(defaultValue = "") String filter) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findAll(filter));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findById(id));
    }
}
