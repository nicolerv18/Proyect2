package com.store.record_store.modules.branch.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.record_store.modules.branch.dto.BranchRequestDto;
import com.store.record_store.modules.branch.dto.BranchResponseDto;
import com.store.record_store.modules.branch.mapper.BranchMapper;
import com.store.record_store.modules.branch.model.Branch;
import com.store.record_store.modules.branch.repository.BranchRepository;
import com.store.record_store.modules.branch.services.IbranchService;

@Service

public class BranchService implements IbranchService {

    @Autowired
    public BranchRepository data;
    @Autowired
    public BranchMapper mapper;

    @Override
    public BranchResponseDto create(BranchRequestDto requestDto) {
        Branch branch = mapper.toEntity(requestDto);
        Branch saved = data.save(branch);
        return mapper.toDto(saved);
    }

    @Override
    public List<BranchResponseDto> findAll(String filter) {
        return mapper.toDtoList(data.findAll(filter));
    }

    @Override
    public BranchResponseDto findById(UUID id) {
        var branch = data.findById(id);
        if (branch.isEmpty())
            return null;
        return mapper.toDto(branch.get());
    }
}
