package com.cyberguard.cyberguard.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cyberguard.cyberguard.entity.Alerta;
import com.cyberguard.cyberguard.repository.AlertaRepository;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private AlertaRepository alertaRepository;

    @Override
    public void run(String... args) throws Exception {
        // Só insere se o banco estiver vazio
        if (alertaRepository.count() == 0) {
            
            Alerta alerta1 = new Alerta();
            alerta1.setTitulo("Golpe do Pix com Devolução");
            alerta1.setTexto("Criminosos se passam por suporte de bancos ou amigos e pedem para você fazer um Pix com a promessa de devolver em dobro ou corrigir um suposto erro. Nunca faça transferências apenas com base em mensagens.");
            
            Alerta alerta2 = new Alerta();
            alerta2.setTitulo("Assinaturas Automáticas em Testes Grátis");
            alerta2.setTexto("Sites e aplicativos oferecem 'teste grátis' e escondem nas letras miúdas que a assinatura é renovada automaticamente. Leia sempre os termos.");
            
            Alerta alerta3 = new Alerta();
            alerta3.setTitulo("Links de Rastreamento de Encomenda");
            alerta3.setTexto("Golpistas enviam SMS com links falsos de rastreamento. Nunca clique em links recebidos por mensagem. Acesse o site oficial direto no navegador.");

            Alerta alerta4 = new Alerta();
            alerta4.setTitulo("Pix Programado");
            alerta4.setTexto("Criminosos combinam um Pix programado, mas pedem que você antecipe algum valor. Desconfie de qualquer pedido de adiantamento.");

            alertaRepository.saveAll(Arrays.asList(alerta1, alerta2, alerta3, alerta4));
            System.out.println("Alertas inseridos no PostgreSQL com sucesso!");
        }
    }
}