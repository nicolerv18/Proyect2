package com.store.record_store.modules.artist.dto;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtistRequestDto {

    @NotBlank
    @Size(max = 15)
    private String name;

    @NotNull
    private UUID countryId;

    @NotNull
    private LocalDate startDate;

    @Size(max = 20)
    private String description;

    @NotBlank
    @Size(max = 20)
    private String status;
}
