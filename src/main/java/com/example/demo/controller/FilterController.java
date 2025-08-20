package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.TarefaDto;
import com.example.demo.controller.factory.TarefaRestFactory;
import com.example.demo.model.TarefaModel;
import com.example.demo.services.TarefaService;

@RestController
@RequestMapping("/filter")
public class FilterController {

    private final TarefaRestFactory tarefaRestFactory;

    private final TarefaService tarefaService;

    public FilterController(TarefaService tarefaService, TarefaRestFactory tarefaRestFactory) {
        this.tarefaService = tarefaService;
        this.tarefaRestFactory = tarefaRestFactory;
    }

    @GetMapping("{date}")
    public ResponseEntity<TarefaDto> getTarefaByDay(@PathVariable String date) {
        LocalDateTime data = tarefaService.StringtoDate(date);
        List<TarefaModel> tarefaModel = (tarefaService.findByDate(data));
        return ResponseEntity.ok().body(TarefaRestFactory.toDto(tarefaModel));

    }
    
    
}
