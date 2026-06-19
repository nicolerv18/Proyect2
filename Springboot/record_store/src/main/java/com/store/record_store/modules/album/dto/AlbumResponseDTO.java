package com.store.record_store.modules.album.dto;

import java.time.LocalDate;
import java.util.UUID;
import lombok.Data;

@Data
public class AlbumResponseDTO {

    private UUID id;
    private String title;
    private LocalDate releaseDate;
    private Float price;
    private Integer stock;
    private String format;
    private String status;
}
