package com.example.demo.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.TarefaDto;
import com.example.demo.controller.factory.TarefaRestFactory;
import com.example.demo.model.TarefaModel;
import com.example.demo.repository.TarefaRepository;
import com.example.demo.services.TarefaService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/tarefa")
public class TarefaController<UUID> {

    private final TarefaRepository tarefaRepository;

    private final TarefaRestFactory tarefaRestFactory;

    @Autowired
    private TarefaService tarefaService;


    TarefaController(TarefaRestFactory tarefaRestFactory, TarefaRepository tarefaRepository) {
        this.tarefaRestFactory = tarefaRestFactory;
        this.tarefaRepository = tarefaRepository;
    }


//    public tarefaController(TarefaService tarefaService) {
//        this.tarefaService = TarefaService;
//    } construtor

    @PostMapping
    public ResponseEntity<TarefaDto> postTarefa(@RequestBody @Valid TarefaDto tarefaDto) {
        tarefaDto.setCriado(LocalDateTime.now(ZoneId.systemDefault()).toString());
        TarefaModel tarefaModel = TarefaRestFactory.toEntity(tarefaDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(TarefaRestFactory.toDto(tarefaService.save(tarefaModel)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaDto> getTarefa(@PathVariable Long id){
        return ResponseEntity.ok().body(TarefaRestFactory.toDto(tarefaService.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaDto> putTarefa(@PathVariable Long id, @RequestBody @Valid TarefaDto tarefaDto) {
        TarefaModel tarefaModel = TarefaRestFactory.toEntity(tarefaDto);
            return ResponseEntity.ok().body(TarefaRestFactory.toDto(tarefaService.save(tarefaModel)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTarefa(@PathVariable Long id) {
        if(tarefaRepository.equals(id)){
            tarefaService.delete(id);
        }
        return ResponseEntity.ok("APAGADO");
        
    }
    
    
    

}
