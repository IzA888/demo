package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.example.demo.Dto.TarefaDto;
import com.example.demo.Dto.UserDto;
import com.example.demo.repository.TarefaRepository;


public class TesteIntegracaoTarefa {

    @Mock
    TarefaRepository tarefaRepository;
    
    @Autowired
    TestRestTemplate testRestTemplate;

    private Long id;

    @BeforeEach
    void login() {
        UserDto userDto = new UserDto("Fulano da Silva", "fulano1", "1234");
        ResponseEntity<UserDto> responseUserDto = testRestTemplate.postForEntity("/user", userDto, UserDto.class);
        
        String auth = ResponseEntity.status(200).build().getHeaders().get("Authorization").get(0); ;



        assertEquals(responseUserDto.getStatusCode(), ResponseEntity.status(201).build().getStatusCode());
        assertEquals(responseUserDto.getBody().getUsername(), userDto.getUsername());
    }

    @Test
    public void testeCreateTarefa() {
        TarefaDto tarefaDto = new TarefaDto("Estudar Spring", "Estudar Spring Boot e Spring Security", LocalDateTime.parse("2025-02-01'T'00:00:00"), 2.0, false);
        ResponseEntity<TarefaDto> responseTarefaDto = testRestTemplate.postForEntity("/tarefa", tarefaDto, TarefaDto.class);

        assertEquals(responseTarefaDto.getStatusCode(), ResponseEntity.status(201).build().getStatusCode());
        assertEquals(responseTarefaDto.getBody().getNome(), tarefaDto.getNome());
    }

    @Test
    public void testeGetTarefaById() {
        Long id = 1L;
        ResponseEntity<TarefaDto> responseTarefaDto = testRestTemplate.getForEntity("/tarefa/" + id, TarefaDto.class);

        assertEquals(responseTarefaDto.getStatusCode(), ResponseEntity.status(200).build().getStatusCode());
        assertEquals(responseTarefaDto.getBody(), tarefaRepository.findById(id));
    }

    @Test
    public void testeGetTarefaByNome() {
        String nome = "Estudar Spring";
        ResponseEntity<TarefaDto> responseTarefaDto = testRestTemplate.getForEntity("/tarefa/nome?nome=" + nome, TarefaDto.class);

        assertEquals(responseTarefaDto.getStatusCode(), ResponseEntity.status(200).build().getStatusCode());
        assertEquals(responseTarefaDto.getBody().getNome(), nome);
    }

    @Test
    public void testeDeleteTarefa() {
        Long id = 1L;
        testRestTemplate.delete("/tarefa/" + id);
        TarefaDto responseTarefaDto = testRestTemplate.getForObject("/tarefa/" + id, TarefaDto.class);

        assertEquals(responseTarefaDto , null);
    }

    @Test
    public void testeUpdateTarefa() {
        Long id = 1L;
        TarefaDto tarefaDto = new TarefaDto("Estudar Spring Boot", "Estudar Spring Boot e Spring Security", LocalDateTime.parse("2023-10-01T00:00:00"), 2.0, false);
        testRestTemplate.put("/tarefa/" + id, tarefaDto);
        ResponseEntity<TarefaDto> responseTarefaDto = testRestTemplate.getForEntity("/tarefa/" + id, TarefaDto.class);

        assertEquals(responseTarefaDto.getStatusCode(), ResponseEntity.status(200).build().getStatusCode());
        assertEquals(responseTarefaDto.getBody().getNome(), tarefaDto.getNome());
    }

    @Test
    public void testeGetAllTarefas() {
        ResponseEntity<TarefaDto[]> responseTarefaDto = testRestTemplate.getForEntity("/tarefa/allTarefas", TarefaDto[].class);

        assertEquals(responseTarefaDto.getStatusCode(), ResponseEntity.status(200).build().getStatusCode());
        assertEquals(responseTarefaDto.getBody().length, 1);
    }

    @Test
    public void testeGetTarefaByMonth() {
        String date = "2023-10-01T00:00:00";
        ResponseEntity<TarefaDto[]> responseTarefaDto = testRestTemplate.getForEntity("/tarefa/getTarefaByMonth?date=" + date, TarefaDto[].class);

        assertEquals(responseTarefaDto.getStatusCode(), ResponseEntity.status(200).build().getStatusCode());
        assertEquals(responseTarefaDto.getBody().length, 1);
    }

    @Test
    public void testeGetTarefaByWeek() {
        String date = "2023-10-01T00:00:00";
        ResponseEntity<TarefaDto[]> responseTarefaDto = testRestTemplate.getForEntity("/tarefa/getTarefaByWeek?date=" + date, TarefaDto[].class);

        assertEquals(responseTarefaDto.getStatusCode(), ResponseEntity.status(200).build().getStatusCode());
        assertEquals(responseTarefaDto.getBody().length, 1);
    }

    @Test
    public void testeGetTarefaByDate() {
        String date = "2023-10-01";
        ResponseEntity<TarefaDto[]> responseTarefaDto = testRestTemplate.getForEntity("/tarefa/data?data=" + date, TarefaDto[].class);

        assertEquals(responseTarefaDto.getStatusCode(), ResponseEntity.status(200).build().getStatusCode());
        assertEquals(responseTarefaDto.getBody().length, 1);
    }

}

