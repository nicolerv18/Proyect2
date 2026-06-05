package com.store.record_store.modules.user.dto;
import lombok.Data;

@Data
public class userResponseDTO {
    private int id;
    private String first_Name;
    private String last_Name;
    private String email;
    private String phone;
    private String address;
    private boolean status;
    private java.time.LocalDateTime createdAt;
    private java.time.LocalDateTime updatedAt;
    
}
