package com.cyberguard.cyberguard.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnore; // 1. Importação adicionada aqui

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  
    private int pontuacao = 0;

    private String nome;
    private String email;
    private String senha;

    public Usuario() {} 

    public Long getId() { return id; }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    // 2. Anotação adicionada aqui para esconder a senha do Frontend
    @JsonIgnore
    public String getSenha() { return senha; }
    
    public void setSenha(String senha) { this.senha = senha; }
}