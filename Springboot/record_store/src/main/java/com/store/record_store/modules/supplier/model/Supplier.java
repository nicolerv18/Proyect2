package com.store.record_store.modules.supplier.model;
import com.store.record_store.shared.ABaseEntity.ABaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "supplier")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Supplier extends ABaseEntity {
    @Column(name = "company_name", length = 50, nullable = false)
    private String companyName;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "email", length = 30)
    private String email;

    @Column(name = "address", length = 35)
    private String address;

}
