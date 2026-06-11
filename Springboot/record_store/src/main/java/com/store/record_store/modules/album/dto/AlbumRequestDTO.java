package com.store.record_store.modules.Album.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class AlbumRequestDTO {
    @NotBlank( message = "Por favor digite el título del álbum") 
    @Size(max = 25 )
    private String title;

    @NotBlank ( message = "La fecha de lanzamiento es obligatoria")
    private String releaseDate;

    @NotBlank ( message = "El precio es obligatorio ")
    private Float price;

    @NotBlank(message = "El stock es obligatorio")
    private Integer stock;

    @NotBlank(message = "El formato es obligatorio")
    @Size(max = 10)
    private String format;
}
