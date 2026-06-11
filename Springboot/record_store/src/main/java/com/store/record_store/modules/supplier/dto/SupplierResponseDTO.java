package com.store.record_store.modules.supplier.dto;

import lombok.Data;

@Data
public class SupplierResponseDTO {
    private Long id;
    private String companyName;
    private String phone;
    private String email;
    private String address;
    private String status;

}
