package com.cyberguard.cyberguard.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import com.fasterxml.jackson.annotation.JsonProperty; // 1. O import mudou aqui!

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  
    private int pontuacao = 0;

    private String nome;
    private String email;
    
    // 2. A anotação correta vem para cá!
    // Isso diz ao Spring: "Pode receber a senha no cadastro, mas NUNCA devolva ela na resposta"
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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

    // 3. O getter volta a ficar "limpo", sem nenhuma anotação
    public String getSenha() { return senha; }
    
    public void setSenha(String senha) { this.senha = senha; }
}