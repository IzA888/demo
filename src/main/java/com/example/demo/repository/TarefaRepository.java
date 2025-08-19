package com.example.demo.repository;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TarefaModel;

@Repository
public interface TarefaRepository extends JpaRepository<TarefaModel, Long> {
    
    Optional<TarefaModel> findByNome(String nome);

    Optional<TarefaModel> findByData(Date data);

    List<TarefaModel> findAll();



}
