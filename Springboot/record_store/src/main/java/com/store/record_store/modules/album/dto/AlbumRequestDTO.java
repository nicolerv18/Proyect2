package com.store.record_store.modules.album.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AlbumRequestDTO {
    

    private Long supplierId;

    @NotBlank(message = "Por favor digite el título del álbum")
    @Size(max = 25)
    private String title;

    @NotNull(message = "La fecha de lanzamiento es obligatoria")
    private LocalDate releaseDate;

    @NotNull(message = "El precio es obligatorio")
    private Float price;

    @NotNull(message = "El stock es obligatorio")
    private Integer stock;

    @NotBlank(message = "El formato es obligatorio")
    @Size(max = 10)
    private String format;
}
