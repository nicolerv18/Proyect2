package com.store.record_store.modules.user.services;

import com.store.record_store.modules.user.dto.LoginRequestDTO;
import com.store.record_store.modules.user.dto.LoginResponseDTO;
import com.store.record_store.modules.user.dto.UserRequestDTO;
import com.store.record_store.modules.user.dto.UserResponseDTO;
import com.store.record_store.modules.user.model.User;
import com.store.record_store.shared.service.ABaseService;

import java.util.UUID;

public interface UserService extends ABaseService<User, UUID, UserRequestDTO, UserResponseDTO> {

    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}
