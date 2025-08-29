package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.Dto.UserDto;

import io.micrometer.core.ipc.http.HttpSender.Response;

@ActiveProfiles("test")
public class TesteIntegracaoUser {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void testeCreateUser() {
        UserDto userDto = new UserDto("Fulano da Silva", "fulano1", "1234");

        ResponseEntity<List<UserDto>> responseUserDto = testRestTemplate.postForEntity("/user", userDto, List.class);

        assertEquals(userDto, responseUserDto);
        assertEquals(responseUserDto.getStatusCode(), ResponseEntity.status(201).build().getStatusCode());
    }

    @Test
    public void testeGetUser() {
        ResponseEntity<UserDto> responseUserDto = testRestTemplate.getForEntity("/user", UserDto.class);

        assertEquals(responseUserDto , UserDto.class);
        assertEquals(responseUserDto.getStatusCode(), ResponseEntity.status(200).build().getStatusCode());
        assertEquals(responseUserDto.getBody().getNome(), 1);
    }

    @Test
    public void testeGetUserByUsername() {
        String username = "fulano1";
        ResponseEntity<UserDto> responseUserDto = testRestTemplate.getForEntity("/user/" + username, UserDto.class);

        assertEquals(responseUserDto.getStatusCode(), ResponseEntity.status(200).build().getStatusCode());
        assertEquals(responseUserDto.getBody().getUsername(), username);
        assertEquals(responseUserDto.getBody().getNome(), 1);
    }
    
    @Test
    public void testeDeleteUser() {
        Long id = 1L;
        testRestTemplate.delete("/user/" + id);
        UserDto responseUserDto = testRestTemplate.getForObject("/user", UserDto.class);

        assertEquals(responseUserDto , null);
    }
    

}
