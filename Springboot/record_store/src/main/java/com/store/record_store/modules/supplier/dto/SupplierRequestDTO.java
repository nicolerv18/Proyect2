package com.store.record_store.modules.supplier.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class SupplierRequestDTO {
    @NotBlank( message = "Por favor digite el nombre de la disquera") 
    @Size(max = 50 )
    private String companyName;

    @NotBlank ( message = "El teléfono es obligatorio")
    @Size(max = 20)
    private String phone;

    @NotBlank ( message = "El correo es obligatorio ")
    @Email ( message = "El correo es inválido")
    @Size (max = 30)
    private String email;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 35)
    private String address;
}
