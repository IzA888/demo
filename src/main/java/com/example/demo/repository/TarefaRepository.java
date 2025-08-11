package com.example.demo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TarefaModel;

@Repository
public interface TarefaRepository extends JpaRepository<TarefaModel, UUID> {

}
