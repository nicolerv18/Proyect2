package com.store.record_store.modules.genre.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenreRequest {

    @NotBlank(message = "el nombre no puede ser nulo")
    @Size (min= 2, max = 20)
    private String name;

    @NotBlank(message = "La descripcion es obligatoria")
    @Size (min= 10, max = 30)
    private String description;

    
}
