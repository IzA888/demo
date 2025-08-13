package com.example.demo.controller.factory;

import org.springframework.stereotype.Component;

import com.example.demo.Dto.UserDto;
import com.example.demo.model.UserModel;

@Component
public class UserRestFactory {
    
    public static UserDto toDto(UserModel userModel) {
        UserDto dto = new UserDto();
        
        dto.setNome(userModel.getNome());
        dto.setUsername(userModel.getUsername());
        dto.setSenha(userModel.getSenha());

        return dto;
    }

    public static UserModel toEntity(UserDto userDto) {
        UserModel entity = new UserModel();
        
        entity.setNome(userDto.getNome());
        entity.setUsername(userDto.getUsername());
        entity.setSenha(userDto.getSenha());

        return entity;
    }
}
