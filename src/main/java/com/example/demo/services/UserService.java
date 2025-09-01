package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
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

    public UserModel findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
