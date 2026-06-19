package com.store.record_store.shared.ABaseEntity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ABaseEntity {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private UUID id;

    

    @Column(name = "created_at", nullable = false, updatable = false)
    protected java.time.LocalDateTime createdAt;
    @Column(name = "updated_at")
    protected java.time.LocalDateTime updatedAt;

    
    
}
