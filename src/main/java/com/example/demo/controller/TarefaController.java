package com.example.demo.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


//    public tarefaController(TarefaService tarefaService) {
//        this.tarefaService = TarefaService;
//    } construtor

    @PostMapping
    public ResponseEntity<TarefaDto> postTarefa(@RequestBody @Valid TarefaDto tarefaDto) {
        TarefaModel tarefaModel = TarefaRestFactory.toEntity(tarefaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(TarefaRestFactory.toDto(tarefaService.save(tarefaModel)));
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<TarefaDto> getTarefa(@PathVariable Long id){
//        return ResponseEntity.ok().body(TarefaRestFactory.toDto(tarefaService.findById(id)));
//    }


    @GetMapping("{nome}")
    public ResponseEntity<TarefaDto> getTarefaNome(@PathVariable String nome) {
        return ResponseEntity.ok().body(TarefaRestFactory.toDto(tarefaService.findTarefaByNome(nome))) ;
    }

    @GetMapping(path = "/getTarefaByDay")
    public ResponseEntity<TarefaDto> getTarefaByDay(@RequestParam("date")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        List<TarefaModel> tarefaModel = (tarefaService.findByDate(date.atZone(ZoneId.systemDefault()).toLocalDateTime()));
        Integer dia = date.getDayOfMonth();
        tarefaModel.stream()
            .filter(tarefa -> tarefa.getdata().getDayOfMonth() == dia)
            .forEach(System.out::println);
        return ResponseEntity.ok().body(TarefaRestFactory.toDto(tarefaModel.listIterator().next()));

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

    @GetMapping("/agora")
    public String getMethodName() {
        Locale brasil = new Locale("pt", "BR");
        TimeZone zonaSaoPaulo = TimeZone.getTimeZone("America/Sao_Paulo");
        Calendar rightNow = Calendar.getInstance(zonaSaoPaulo);
        return rightNow.getTime().toString();
    }

    @GetMapping("/mes")
    public String getMethodName2() {
        Locale brasil = new Locale("pt", "BR");
        TimeZone zonaSaoPaulo = TimeZone.getTimeZone("America/Sao_Paulo");
        Calendar mes = Calendar.getInstance(zonaSaoPaulo);
        mes.getTime();
        return mes.getDisplayName(Calendar.MONTH, Calendar.LONG, brasil).toString();
    }

    @GetMapping("/dia")
    public String getMethodName3() {
        Locale brasil = new Locale("pt", "BR");
        TimeZone zonaSaoPaulo = TimeZone.getTimeZone("America/Sao_Paulo");
        Calendar dia = Calendar.getInstance(zonaSaoPaulo);
        dia.getTime();
        return dia.getDisplayName(Calendar.DAY_OF_MONTH, Calendar.LONG, brasil);
    }
    
    

}
