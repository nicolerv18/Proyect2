package com.store.record_store.modules.supplier.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class SupplierResponseDTO {
    private UUID id;
    private String companyName;
    private String phone;
    private String email;
    private String address;
    private String status;

}
