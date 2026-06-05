package com.store.record_store.modules.supplier.dto;

import lombok.data;

@data
public class SupplierRequestDTO {
    private String company_name;
    private String phone;
    private String email;
    private String address;
}
