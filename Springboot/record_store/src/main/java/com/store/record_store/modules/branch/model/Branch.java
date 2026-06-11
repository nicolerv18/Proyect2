package com.store.record_store.modules.branch.model;

import com.store.record_store.shared.ABaseEntity.ABaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;             
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table (name = "branch")
@Getter @Setter
@SuperBuilder
@NoArgsConstructor

public class Branch extends ABaseEntity {

  @Column (name = "name", length = 30)
  private String name;

  @Column (name = "address", length = 35)
  private String address;

  @Column (name = "city", length = 30)
  private String city;

  @Column (name = "phone", length = 20)
  private String phone;

  @Column (name = "status")
  private Boolean status;

    
}
