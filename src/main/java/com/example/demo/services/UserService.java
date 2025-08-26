package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService{

    @Autowired
    UserRepository userRepository;

    BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public UserModel save(UserModel userModel) {
        userModel.setSenha(passwordEncoder.encode(userModel.getSenha()));
        return userRepository.save(userModel);
    }

    public Optional<UserModel> findById(Long id) {
        return userRepository.findById(id);
    }

    public UserModel loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public boolean existsByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public Boolean passwordMatches(String rawPassword, String encodedPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
    }

    public UserModel getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        return userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + username));

    }

    public Long getAuthenticatedUserId() {
        return getAuthenticatedUser().getId();
    }

}
