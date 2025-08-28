package com.example.demo.controller;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import com.example.demo.model.UserModel;
import com.example.demo.repository.TarefaRepository;
import com.example.demo.services.TarefaService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;



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

    @PostMapping
    public ResponseEntity<TarefaDto> postTarefa(@RequestBody @Valid TarefaDto tarefaDto) {
        TarefaModel tarefaModel = TarefaRestFactory.toEntity(tarefaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(TarefaRestFactory.toDto(tarefaService.save(tarefaModel)));
    }

    @GetMapping("{id}")
    public ResponseEntity<TarefaDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(TarefaRestFactory.toDto(tarefaService.findById(id))) ;
    }

    @GetMapping(path = "{nome}")
    public ResponseEntity<TarefaDto> getTarefaNome(@RequestParam("nome") String nome) {
        return ResponseEntity.ok().body(TarefaRestFactory.toDto(tarefaService.findTarefaByNome(nome))) ;
    }

    @GetMapping(path = "/getTarefaByDay")
    public ResponseEntity<List<TarefaDto>> getTarefaByDay(@RequestParam("date")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        List<TarefaModel> tarefaModel = (tarefaService.findByDate(date.atZone(ZoneId.systemDefault()).toLocalDateTime()));
        Integer dia = date.getDayOfMonth();
        tarefaModel.stream()
            .filter(tarefa -> tarefa.getData().getDayOfMonth() == dia)
            .forEach(System.out::println);
        return ResponseEntity.ok().body(tarefaModel.stream()
            .map(TarefaRestFactory::toDto)
            .collect(Collectors.toList()));
    }

    @GetMapping(path = "/getTarefaByWeek")
    public ResponseEntity<List<TarefaDto>> getTarefaByWeek(@RequestParam("date")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        List<TarefaModel> tarefaModel = (tarefaService.findAllDatas());
        // Regras da localidade (ex: Brasil começa a semana na segunda)
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int semana = date.get(weekFields.weekOfWeekBasedYear());
        // Filtra as tarefas pela semana
        return ResponseEntity.ok().body(tarefaModel.stream()
            .filter(tarefa -> tarefa.getData().get(weekFields.weekOfWeekBasedYear()) == semana)
            .map(TarefaRestFactory::toDto)
            .collect(Collectors.toList()));
    }

    @GetMapping(path = "/getTarefaByMonth")
    public ResponseEntity<List<TarefaDto>> getTarefaByMonth(@RequestParam("date")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        List<TarefaModel> tarefaModel = (tarefaService.findAllDatas());
        Integer mes = date.getMonthValue();
        return ResponseEntity.ok().body(tarefaModel.stream()
            .filter(tarefa -> tarefa.getData().getMonthValue() == mes)
            .map(TarefaRestFactory::toDto)
            .collect(Collectors.toList()));
    }

    @GetMapping(path = "/allTarefas")
    public ResponseEntity<List<TarefaDto>> getAllTarefas() {
        List<TarefaModel> allTarefas = tarefaService.getTarefasdeUsuarioLogado();
        return ResponseEntity.ok().body(allTarefas.stream()
            .map(TarefaRestFactory::toDto)
            .collect(Collectors.toList()));
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
