package com.store.record_store.modules.user.services.impl;

import com.store.record_store.modules.user.dto.LoginRequestDTO;
import com.store.record_store.modules.user.dto.LoginResponseDTO;
import com.store.record_store.modules.user.dto.UserRequestDTO;
import com.store.record_store.modules.user.dto.UserResponseDTO;
import com.store.record_store.modules.user.mapper.UserMapper;
import com.store.record_store.modules.user.model.User;
import com.store.record_store.modules.user.repository.UserRepository;
import com.store.record_store.modules.user.services.UserService;
import com.store.record_store.shared.service.impl.ABaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl 
    extends ABaseServiceImpl<User, UUID, UserRequestDTO, UserResponseDTO, UserRepository>
    implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
        super();
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }


    @Override
    protected UserResponseDTO toResponse(User entity) {
        return userMapper.toResponse(entity);
    }

    @Override
    protected User toEntity(UserRequestDTO dto) {
        return userMapper.toEntity(dto);
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        // Buscar usuario por email
        User user = userRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario o contraseña incorrectos"));

        if (!user.getPassword().equals(loginRequestDTO.getPassword())) {
            throw new RuntimeException("Usuario o contraseña incorrectos");
        }

        LoginResponseDTO response = new LoginResponseDTO();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        
        return response;
    }

    public UserResponseDTO getByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return toResponse(user);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}