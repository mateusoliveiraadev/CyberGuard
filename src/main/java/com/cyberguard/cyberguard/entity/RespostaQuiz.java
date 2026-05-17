package com.cyberguard.cyberguard.entity;

import java.util.List;

public class RespostaQuiz {

    private String emailUsuario; 
    private List<String> respostas;

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public List<String> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<String> respostas) {
        this.respostas = respostas;
    }
}