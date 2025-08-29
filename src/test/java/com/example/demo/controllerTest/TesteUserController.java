package com.example.demo.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.example.demo.Dto.UserDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TesteUserController {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void testeCreateUser() {
        UserDto userDto = new UserDto("Fulano da Silva", "fulano1", "1234");

        List<UserDto> responseUserDto = testRestTemplate.postForObject("/user", userDto, List.class);

        assertEquals(responseUserDto, responseUserDto);
    }

    @Test
    public void testeGetUser() {
        UserDto responseUserDto = testRestTemplate.getForObject("/user", UserDto.class);

        assertEquals(responseUserDto , UserDto.class);
    }

    @Test
    public void testeGetUserByUsername() {
        String username = "fulano1";
        UserDto responseUserDto = testRestTemplate.getForObject("/user/" + username, UserDto.class);

        assertEquals(responseUserDto.getUsername(), username);
    }
    
    @Test
    public void testeDeleteUser() {
        Long id = 1L;
        testRestTemplate.delete("/user/" + id);
        UserDto responseUserDto = testRestTemplate.getForObject("/user", UserDto.class);

        assertEquals(responseUserDto , null);
    }
    

}
