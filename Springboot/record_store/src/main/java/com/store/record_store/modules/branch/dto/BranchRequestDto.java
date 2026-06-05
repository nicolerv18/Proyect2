package com.store.record_store.modules.branch.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor

public class BranchRequestDto {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 30, message = "El nombre no puede superar 30 caracteres")
    private String name;

    @NotBlank( message = "la direccion es obligatoria")
    @Size(max = 35, message = "la direccion no puede superar 35 caracteres")
    private String address;

    @NotBlank(message = "La ciudad es obligatoria")
    @Size(max = 30, message = "La ciudad no puede superar 30 caracteres")
    private String city;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(max = 20, message = "El teléfono no puede superar 20 caracteres")
    private String phone;

    @NotNull(message = "El estado es obligatorio")
    private Boolean status;
    


}
