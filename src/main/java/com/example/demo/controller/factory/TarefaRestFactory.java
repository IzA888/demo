package com.example.demo.controller.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.demo.Dto.TarefaDto;
import com.example.demo.model.TarefaModel;

@Component
public class TarefaRestFactory {

    public static TarefaDto toDto(Optional<TarefaModel> tarefaModel){
       TarefaDto dto = new TarefaDto();
       
       dto.setNome(tarefaModel.get().getNome());
       dto.setDescricao(tarefaModel.get().getDescricao());
       dto.setData(tarefaModel.get().getData());
       dto.setDuracao(tarefaModel.get().getDuracao());
       dto.setCriado(tarefaModel.get().getCriado());
       dto.setCompleto(tarefaModel.get().getCompleto());

        return dto;
    }

    public static TarefaDto toDto(TarefaModel tarefaModel){
       TarefaDto dto = new TarefaDto();
       
       dto.setNome(tarefaModel.getNome());
       dto.setDescricao(tarefaModel.getDescricao());
       dto.setData(tarefaModel.getData());
       dto.setDuracao(tarefaModel.getDuracao());
       dto.setCriado(tarefaModel.getCriado());
       dto.setCompleto(tarefaModel.getCompleto());

        return dto;
    }

    public static TarefaModel toEntity(TarefaDto tarefaDto){
        TarefaModel entity = new TarefaModel();

        entity.setNome(tarefaDto.getNome());
        entity.setDescricao(tarefaDto.getDescricao());
        entity.setData(tarefaDto.getData());
        entity.setDuracao(tarefaDto.getDuracao());
        entity.setCriado(tarefaDto.getCriado());
        entity.setCompleto(tarefaDto.getCompleto());

        return entity;

    }
    
    public static TarefaModel toEntity(Optional<TarefaDto> tarefaDto){
        TarefaModel entity = new TarefaModel();

        entity.setNome(tarefaDto.get().getNome());
        entity.setDescricao(tarefaDto.get().getDescricao());
        entity.setData(tarefaDto.get().getData());
        entity.setDuracao(tarefaDto.get().getDuracao());
        entity.setCriado(tarefaDto.get().getCriado());
        entity.setCompleto(tarefaDto.get().getCompleto());

        return entity;

    }

    public static List<TarefaDto> toDto(List<TarefaModel> tarefaModel) {
       List<TarefaDto> dto = new ArrayList<>();
       
       dto.stream()
            .map(tarefa -> new TarefaDto(tarefa.getNome(), tarefa.getDescricao(), tarefa.getData(), tarefa.getDuracao(), tarefa.getCriado(), tarefa.getCompleto()))
            .collect(Collectors.toList());

        return dto;
    }    
    
    
}
