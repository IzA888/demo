package com.example.demo.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.TarefaModel;
import com.example.demo.repository.TarefaRepository;

import jakarta.transaction.Transactional;

@Service
public class TarefaService {

    @Autowired
    private AuthService authService;

    @Autowired
    TarefaRepository tarefaRepository;


    @Transactional
    public TarefaModel save(TarefaModel tarefaModel) {
        tarefaModel.setUser(authService.getAuthenticatedUser());
        return tarefaRepository.save(tarefaModel);
    }

    public List<TarefaModel> getTarefasdeUsuarioLogado() {
        return tarefaRepository.findByUser(authService.getAuthenticatedUser()).stream().toList();
    }

    public TarefaModel findById(Long id) {
        return tarefaRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada: " + id));
    }

    public TarefaModel findTarefaByNome(String nome) {
        return tarefaRepository.findByNome(nome).orElseThrow(() -> new RuntimeException("Tarefa não encontrada: " + nome));
    }

    @Transactional
    public void delete(Long id) {
        tarefaRepository.deleteById(id);
    }


    public LocalDateTime StringtoDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
        LocalDateTime data = LocalDateTime.from(LocalDateTime.parse(date, formatter));
        return data;
    }
    
    public List<TarefaModel> findByDate(LocalDateTime data) {
        List<TarefaModel> lista = tarefaRepository.findByData(data);
        if (!lista.isEmpty()) {
            return lista;
        }else {
            System.out.println("Tarefa não encontrada para a data: " + data);
            return List.of();
        }
    }

    public List<TarefaModel> findAllDatas() {
        List<TarefaModel> lista = tarefaRepository.findAll();
        if (!lista.isEmpty()) {
            return lista;
        }else {
            System.out.println("Tarefa não encontrada" );
            return List.of();
        }
    }
}
