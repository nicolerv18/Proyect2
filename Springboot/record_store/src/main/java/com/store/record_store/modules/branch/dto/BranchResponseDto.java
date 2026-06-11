package com.store.record_store.modules.branch.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor

public class BranchResponseDto {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String phone;
    private Boolean status;
}
