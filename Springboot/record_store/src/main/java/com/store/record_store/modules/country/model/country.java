package com.store.record_store.modules.country.model;
import com.store.record_store.shared.ABaseEntity.ABaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "country")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Builder
public class country extends ABaseEntity {

    @Column(name ="name" , nullable = false, length = 20)
    private String name;

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
