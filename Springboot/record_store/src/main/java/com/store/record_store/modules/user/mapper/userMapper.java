package com.store.record_store.modules.user.mapper;
import java.util.List;

import com.store.record_store.modules.user.dto.userRequestDTO;
import com.store.record_store.modules.user.dto.userResponseDTO;
import com.store.record_store.modules.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface userMapper {

@Mapping(target= "id", ignore = true)
@Mapping(target= "status", constant = "ACTIVE")
@Mapping(target= "createdAt", ignore = true)
@Mapping(target= "updatedAt", ignore = true)
User toEntity(userRequestDTO dto);
userResponseDTO toDTO(User entity);
List<userResponseDTO> toDTOList(List<User> entities);



    
}
