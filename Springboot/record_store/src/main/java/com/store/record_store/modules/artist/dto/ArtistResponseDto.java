package com.store.record_store.modules.artist.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtistResponseDto {

    private Long artistId;
    private String name;
    private String countryName;
    private LocalDate startDate;
    private String description;
    private String status;
}
