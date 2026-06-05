package com.store.record_store.modules.user.model;
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
@Table(name = "user")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor


public class User extends ABaseEntity {

    @Column(name ="first_name" , nullable = false, length = 20)
    private String first_Name;

    @Column(name ="last_name" , nullable = false, length = 20)
    private String last_Name;

    @Column(name ="email" , nullable = false, length = 30)
    private String email;

    @Column(name ="password" , nullable = false, length = 64)
    private String password;

    @Column(name ="phone" , nullable = false, length = 255)
    private String phone;
    
    @Column(name ="address" , nullable = false, length = 35)
    private String address;


}
