package com.store.record_store.modules.user.services.impl;

import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Service;
import com.store.record_store.modules.user.services.userService;
import com.store.record_store.modules.user.dto.userRequestDTO;
import com.store.record_store.modules.user.dto.userResponseDTO;
import com.store.record_store.modules.user.dto.loginRequestDTO;
import com.store.record_store.modules.user.mapper.userMapper;

import com.store.record_store.modules.user.dto.loginResponseDTO;

@Service
public class userServiceImpl implements userService {
    
    @Override
    public userResponseDTO createUser(userRequestDTO dto) {
        User user = userMapper.toEntity(dto);
        user.setStatus("Active");
        User usersaved = userRepository.save(user);
        //recibe datos del usuario
        //validar datos
        //encriptar contraseña
        //guardar en la base de datos
        return null;

        }
    
    @Override
    public java.util.List<userResponseDTO> getAll() {

        return null; 
    }

    @Override
    public userResponseDTO getById(int id) {
        // obtener usuario por id de la base de datos
        // convertir a DTO
        return null;
    }

    @Override
    public userResponseDTO updateUser(int id, userRequestDTO dto) {
        //obtener usuario
        //actualizar datos
        //guardar cambios
        return null;

    }

    @Override
    public void deleteUser(int id) {
        //obtener usuario
        //eliminar usuario
        
    }

    @Override
    public loginResponseDTO login(loginRequestDTO dto) {
        //obtener usuario por email
        //comparar contraseña
        //generar token
        return null;
    }  
    
}
