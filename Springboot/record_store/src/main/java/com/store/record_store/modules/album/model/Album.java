package com.store.record_store.modules.album.model;

import java.time.LocalDate;

import com.store.record_store.shared.ABaseEntity.ABaseEntity;
import com.store.record_store.modules.supplier.model.Supplier;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "album")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Album extends ABaseEntity {
    @Column(name = "title",length = 25, nullable = false)
    private String title;

    @Column(name = "release_date", length = 20)
    private LocalDate releaseDate;

    @Column(name = "price", precision = 10, scale = 2)
    private float price;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "format", length = 10)
    private String format;
    
    @Column(name = "status", length = 10)
    private String status;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
}
