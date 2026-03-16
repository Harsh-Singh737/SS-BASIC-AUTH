package com.spring.security.basic.auth.mapper;

import com.spring.security.basic.auth.entity.User;
import com.spring.security.basic.auth.entity.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStructMapper {


    UserDTO toDto(User user);

    User toEntity(UserDTO userDTO);
}
