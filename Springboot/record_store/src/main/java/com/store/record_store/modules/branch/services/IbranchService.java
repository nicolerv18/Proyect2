package com.store.record_store.modules.branch.services;

import java.util.List;
import java.util.UUID;

import com.store.record_store.modules.branch.dto.BranchRequestDto;
import com.store.record_store.modules.branch.dto.BranchResponseDto;

public interface IbranchService {

    /*
    create
    update
    partial
    delete logic
    delete
    */

    public BranchResponseDto create(BranchRequestDto requestDto);
    public List<BranchResponseDto> findAll(String filter);
    public BranchResponseDto findById(UUID id);
}
