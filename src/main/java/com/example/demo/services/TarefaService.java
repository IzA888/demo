package com.example.demo.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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


    public Date StringtoDate(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Date date = Date.from(LocalDate.parse(data, formatter).atStartOfDay(ZoneId.systemDefault()).toInstant());
        return date;
    }
    
    public List<TarefaModel> findByDate(String data) {

        Optional<TarefaModel> Day = tarefaRepository.findByData(StringtoDate(data));
        if (Day.isPresent()) {
            Day.toString().substring(0, 2);
            System.out.println("Tarefa encontrada: " + Day);
            return tarefaRepository.findAll();
        }else {
            System.out.println("Tarefa não encontrada para a data: " + data);
            return List.of();
        }
    }

}
