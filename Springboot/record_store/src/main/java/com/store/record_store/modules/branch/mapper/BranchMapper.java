package com.store.record_store.modules.branch.mapper;

import com.store.record_store.modules.branch.dto.BranchRequestDto;
import com.store.record_store.modules.branch.dto.BranchResponseDto;
import com.store.record_store.modules.branch.model.Branch;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface BranchMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Branch toEntity(BranchRequestDto dto);

    BranchResponseDto toDto(Branch branch);

    List<BranchResponseDto> toDtoList(List<Branch> branches);
}