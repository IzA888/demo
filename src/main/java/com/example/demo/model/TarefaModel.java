package com.example.demo.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name = "TB_TAREFA")

public class TarefaModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", unique = true)
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date data;

    @Column(name = "duracao")
    private Double duracao;
    
    @Column(name = "dt_criacao")
    private String criado;

    @Column(name = "completo")
    private Boolean completo;

    public TarefaModel(){}
    
    public TarefaModel(Long id, String nome, String descricao, Date data, Double duracao, String criado, Boolean completo){
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.duracao = duracao;
        this.criado = criado;
        this.completo = completo;    
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private UserModel user;

    
    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getdata() {
        return data;
    }

    public void setdata(Date data) {
        this.data = data;
    }

    public Double getDuracao() {
        return duracao;
    }

    public void setDuracao(Double duracao) {
        this.duracao = duracao;
    }

    public String getCriado() {
        return criado;
    }

    public void setCriado(String criado) {
        this.criado = criado;
    }

    public Boolean getCompleto() {
        return completo;
    }

    public void setCompleto(Boolean completo) {
        this.completo = completo;
    }

    
}
