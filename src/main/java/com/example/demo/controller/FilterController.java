package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.TarefaDto;
import com.example.demo.controller.factory.TarefaRestFactory;
import com.example.demo.model.TarefaModel;
import com.example.demo.services.TarefaService;

@RestController
@RequestMapping("/filter")
public class FilterController {

    private final TarefaService tarefaService;

    public FilterController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping("{data}")
    public ResponseEntity<TarefaDto> getTarefaByDay(@PathVariable String data) {
        List<TarefaModel> tarefaModel = tarefaService.findByDate(data);
        return ResponseEntity.ok().body(TarefaRestFactory.toDto(tarefaModel));

    }
    
    
}
