package com.example.demo.controller.factory;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.demo.Dto.TarefaDto;
import com.example.demo.model.TarefaModel;

@Component
public class TarefaRestFactory {

    public static TarefaDto toDto(Optional<TarefaModel> tarefaModel){
       TarefaDto dto = new TarefaDto();
       
       dto.setNome(tarefaModel.get().getNome());
       dto.setDescricao(tarefaModel.get().getDescricao());
       dto.setCriado(tarefaModel.get().getCriado());
       dto.setCompleto(tarefaModel.get().getCompleto());

        return dto;
    }

    public static TarefaDto toDto(TarefaModel tarefaModel){
       TarefaDto dto = new TarefaDto();
       
       dto.setNome(tarefaModel.getNome());
       dto.setDescricao(tarefaModel.getDescricao());
       dto.setCriado(tarefaModel.getCriado());
       dto.setCompleto(tarefaModel.getCompleto());

        return dto;
    }

    public static TarefaModel toEntity(TarefaDto tarefaDto){
        TarefaModel entity = new TarefaModel();

        entity.setNome(tarefaDto.getNome());
        entity.setDescricao(tarefaDto.getDescricao());
        entity.setCriado(tarefaDto.getCriado());
        entity.setCompleto(tarefaDto.getCompleto());

        return entity;

    }
    
    public static TarefaModel toEntity(Optional<TarefaDto> tarefaDto){
        TarefaModel entity = new TarefaModel();

        entity.setNome(tarefaDto.get().getNome());
        entity.setDescricao(tarefaDto.get().getDescricao());
        entity.setCriado(tarefaDto.get().getCriado());
        entity.setCompleto(tarefaDto.get().getCompleto());

        return entity;

    }
    
    
}
