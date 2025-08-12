package com.example.demo.services;

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


    public void delete(Long id) {
        tarefaRepository.deleteById(id);
    }



    

}
