package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.TarefaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/tarefa")
public class TarefaController<UUID> {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping("/{id}")
    public String getTarefa(@PathVariable(value = "id") UUID id) {
        tarefa = tarefaService.findById(id);
        return tarefa;
    }
    

}
