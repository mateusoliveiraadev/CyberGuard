package com.cyberguard.cyberguard.entity;

public class Pergunta {

    private String enunciado;
    private String respostaCorreta;
    private String explicacao;

    public Pergunta() {}

    public Pergunta(String enunciado, String respostaCorreta, String explicacao) {
        this.enunciado = enunciado;
        this.respostaCorreta = respostaCorreta;
        this.explicacao = explicacao;
    }

    public String getEnunciado() { return enunciado; }

    public String getRespostaCorreta() { return respostaCorreta; }

    public String getExplicacao() { return explicacao; }
}
