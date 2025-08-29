package com.example.demo.Dto;

import java.time.LocalDateTime;

import org.springframework.validation.annotation.Validated;

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
    
    private LocalDateTime data;
    
    private Double duracao;

    private Boolean completo;

    
    
}
