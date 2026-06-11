package com.store.record_store.modules.branch.services.abst;


import com.store.record_store.modules.branch.dto.BranchRequestDto;
import com.store.record_store.modules.branch.dto.BranchResponseDto;
import com.store.record_store.modules.branch.repository.BranchRepository;
import com.store.record_store.modules.branch.services.IbranchService;


public abstract class Abranch implements IbranchService {

    protected final BranchRepository repository;

    protected Abranch(BranchRepository repository) {
        this.repository = repository;
    }

    @Override
    public abstract BranchResponseDto create(BranchRequestDto requestDto);

    // @Override
    // public abstract List<BranchResponseDto> findAll(String filter);

    // @Override
    // public abstract BranchResponseDto findById(Long id);
}