package com.example.demo.Dto;

import java.time.LocalDateTime;
import java.util.Date;

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
    
    private LocalDateTime data;
    
    private Double duracao;

    private Boolean completo;

    
    
}
