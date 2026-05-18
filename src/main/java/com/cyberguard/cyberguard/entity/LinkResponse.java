package com.cyberguard.cyberguard.entity;

public class LinkResponse {


    private boolean seguro;
    private String mensagem;

    public LinkResponse(boolean seguro, String mensagem) {
        this.seguro = seguro;
        this.mensagem = mensagem;
    }

    public boolean isSeguro() {
        return seguro;
    }

    public void setSeguro(boolean seguro) {
        this.seguro = seguro;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}
