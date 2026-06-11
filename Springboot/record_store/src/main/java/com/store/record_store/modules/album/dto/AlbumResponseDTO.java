package com.store.record_store.modules.Album.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class AlbumResponseDTO {

    private Long id;
    private String title;
    private LocalDate releaseDate;
    private Float price;
    private Integer stock;
    private String format;
    private String status;
}