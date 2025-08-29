package com.example.demo.controller;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.UserDto;
import com.example.demo.controller.factory.UserRestFactory;
import com.example.demo.model.UserModel;
import com.example.demo.security.JwtUtil;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    private final JwtUtil jwtUtil = new JwtUtil();


    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> login(@RequestBody UserDto user) {
        UserModel userModel = UserRestFactory.toEntity(user);
        // Check if the user exists and if the password matches
        if (userModel.getUsername() == null || userModel.getPassword() == null) {
            return ResponseEntity.badRequest().body("Username and password must not be empty");
        }
        if (userService.existsByUsername(user.getUsername())) {
            if (!userService.passwordMatches(user.getSenha(), userService.loadUserByUsername(user.getUsername()).getPassword())) {
                return ResponseEntity.status(401).body("Invalid password");
            }
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
        String token = jwtUtil.generateToken(userService.loadUserByUsername(user.getUsername()));
        return ResponseEntity.ok("Login successful, authentication token: " + token);
    }
    
}
