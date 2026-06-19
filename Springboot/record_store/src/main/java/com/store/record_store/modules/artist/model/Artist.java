package com.store.record_store.modules.artist.model;

import java.time.LocalDate;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.store.record_store.modules.country.model.country;

@Entity(name = "artist")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_id")
    private UUID artistId;

    @Column(name = "name", length = 15)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    
    private country country;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "description", length = 20)
    private String description;

    @Column(name = "status", length = 20)
    private String status;
}
