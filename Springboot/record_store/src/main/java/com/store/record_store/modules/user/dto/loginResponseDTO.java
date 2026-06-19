package com.store.record_store.modules.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String token;
    private LocalDateTime loginAt;
}
