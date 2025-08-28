package com.example.demo.Dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class UserDto {

    private String nome;

    private String username;

    @Size(min = 4)
    private String senha;



}

