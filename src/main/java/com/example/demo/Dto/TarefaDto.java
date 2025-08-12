package com.example.demo.Dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class TarefaDto {

    private String nome;

    private String descricao;
    
    @Size(max = 8)
    private String criado;

    private Boolean completo;

    
    
}
