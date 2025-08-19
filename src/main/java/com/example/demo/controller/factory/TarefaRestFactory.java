package com.example.demo.controller.factory;

import java.util.List;
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
       dto.setData(tarefaModel.get().getdata());
       dto.setDuracao(tarefaModel.get().getDuracao());
       dto.setCriado(tarefaModel.get().getCriado());
       dto.setCompleto(tarefaModel.get().getCompleto());

        return dto;
    }

    public static TarefaDto toDto(TarefaModel tarefaModel){
       TarefaDto dto = new TarefaDto();
       
       dto.setNome(tarefaModel.getNome());
       dto.setDescricao(tarefaModel.getDescricao());
       dto.setData(tarefaModel.getdata());
       dto.setDuracao(tarefaModel.getDuracao());
       dto.setCriado(tarefaModel.getCriado());
       dto.setCompleto(tarefaModel.getCompleto());

        return dto;
    }

    public static TarefaModel toEntity(TarefaDto tarefaDto){
        TarefaModel entity = new TarefaModel();

        entity.setNome(tarefaDto.getNome());
        entity.setDescricao(tarefaDto.getDescricao());
        entity.setdata(tarefaDto.getData());
        entity.setDuracao(tarefaDto.getDuracao());
        entity.setCriado(tarefaDto.getCriado());
        entity.setCompleto(tarefaDto.getCompleto());

        return entity;

    }
    
    public static TarefaModel toEntity(Optional<TarefaDto> tarefaDto){
        TarefaModel entity = new TarefaModel();

        entity.setNome(tarefaDto.get().getNome());
        entity.setDescricao(tarefaDto.get().getDescricao());
        entity.setdata(tarefaDto.get().getData());
        entity.setDuracao(tarefaDto.get().getDuracao());
        entity.setCriado(tarefaDto.get().getCriado());
        entity.setCompleto(tarefaDto.get().getCompleto());

        return entity;

    }

    public static TarefaDto toDto(List<TarefaModel> tarefaModel) {
       TarefaDto dto = new TarefaDto();
       
       dto.setNome(tarefaModel.get(0).getNome());
       dto.setDescricao(tarefaModel.get(0).getDescricao());
       dto.setData(tarefaModel.get(0).getdata());   
       dto.setDuracao(tarefaModel.get(0).getDuracao());
       dto.setCriado(tarefaModel.get(0).getCriado());
       dto.setCompleto(tarefaModel.get(0).getCompleto());

        return dto;
    }    
    
    
}
