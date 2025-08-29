package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.Dto.UserDto;

public class DataTesteUser {
    
    public static List<UserDto> generateUsers() {
        List<UserDto> users = new ArrayList<>();
        
        users.add(new UserDto("Fulano da Silva", "fulano1", "1234"));
        users.add(new UserDto("Ciclano de Souza", "ciclano2", "abcd"));
        users.add(new UserDto("Beltrano Pereira", "beltrano3", "xyz123"));
        users.add(new UserDto("Maria Oliveira", "mariao4", "passw0rd"));
        users.add(new UserDto("João Santos", "joaos5", "qwerty"));
        users.add(new UserDto("Ana Costa", "anac6", "letmein"));

        return users;
    }

    public static int randomNumber() {
        return (int) (Math.random() * 6);
    }
}
