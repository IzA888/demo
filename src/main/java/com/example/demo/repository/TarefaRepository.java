package com.example.demo.repository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TarefaModel;
import com.example.demo.model.UserModel;

@Repository
public interface TarefaRepository extends JpaRepository<TarefaModel, Long> {
    
    Optional<TarefaModel> findByNome(String nome);

    List<TarefaModel> findByData(LocalDateTime data);

    @Query("SELECT t FROM TarefaModel t WHERE t.data IS NOT NULL")
    List<TarefaModel> findAll();

    Set<TarefaModel> findByUser(UserModel user);



}
