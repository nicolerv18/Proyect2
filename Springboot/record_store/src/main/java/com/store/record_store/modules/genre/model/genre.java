package com.store.record_store.modules.genre.model;
import com.store.record_store.shared.ABaseEntity.ABaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "genre")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Builder

public class genre extends ABaseEntity {

    @Column(name ="name" , nullable = false, length = 20)
    private String name;

    @Column(name = "description", nullable = false, length = 50)
    private String description;


    @PrePersist
    protected void onCreate() {
        this.createdAt = java.time.LocalDateTime.now();
        this.updatedAt = java.time.LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = java.time.LocalDateTime.now();

}

}
