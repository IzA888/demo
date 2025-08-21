package com.example.demo.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.TarefaModel;
import com.example.demo.repository.TarefaRepository;

import jakarta.transaction.Transactional;

@Service
public class TarefaService {

    @Autowired
    TarefaRepository tarefaRepository;

    @Transactional
    public TarefaModel save(TarefaModel tarefaModel) {
        return tarefaRepository.save(tarefaModel);
    }

    public Optional<TarefaModel> findById(Long id) {
        return tarefaRepository.findById(id);
    }

    public Optional<TarefaModel> findTarefaByNome(String nome) {
        return tarefaRepository.findByNome(nome);
    }

    public void delete(Long id) {
        tarefaRepository.deleteById(id);
    }


    public LocalDateTime StringtoDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
        LocalDateTime data = LocalDateTime.from(LocalDateTime.parse(date, formatter));
        System.out.println(data);
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

}
