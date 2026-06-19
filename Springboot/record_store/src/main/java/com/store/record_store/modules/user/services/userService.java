package com.store.record_store.modules.user.services;
import java.util.List;
import com.store.record_store.modules.user.dto.userRequestDTO;
import com.store.record_store.modules.user.dto.userResponseDTO;
import com.store.record_store.modules.user.dto.loginRequestDTO;
import com.store.record_store.modules.user.dto.loginResponseDTO;

public interface userService {

    userResponseDTO createUser(userRequestDTO dto);
    List<userResponseDTO> getAll();
    userResponseDTO getById(int id);
    userResponseDTO updateUser(int id, userRequestDTO dto);
    void deleteUser(int id);
    loginResponseDTO login(loginRequestDTO dto);
}
