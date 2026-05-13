package com.cyberguard.cyberguard.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cyberguard.cyberguard.entity.Pergunta;

@Service
public class QuizService {

    public List<Pergunta> getPerguntas(String categoria) {
        if ("links".equalsIgnoreCase(categoria)) {
            return Arrays.asList(
                new Pergunta("É seguro clicar em links de SMS que prometem prêmios?", "nao", "Isso é chamado de Smishing. Empresas sérias não dão prêmios via links de SMS genéricos."),
                new Pergunta("Passar o mouse sobre um link antes de clicar ajuda a ver o destino real?", "sim", "Sim, isso permite ver a URL real no canto do navegador e evitar sites falsos."),
                new Pergunta("Encurtadores de links (como bit.ly) podem esconder sites maliciosos?", "sim", "Sim, criminosos usam encurtadores para ocultar o nome de domínios suspeitos.")
            );
        } else if ("pagamentos".equalsIgnoreCase(categoria)) {
            return Arrays.asList(
                new Pergunta("O PIX é um método de pagamento reversível em caso de erro comum?", "nao", "O PIX é instantâneo. Uma vez enviado, é muito difícil recuperar o valor sem a vontade de quem recebeu."),
                new Pergunta("É seguro salvar dados de cartão em redes Wi-Fi públicas?", "nao", "Dados transmitidos em redes públicas podem ser interceptados por hackers próximos."),
                new Pergunta("Cartões virtuais são mais seguros para compras online únicas?", "sim", "Sim, pois você pode excluí-los após a compra, impedindo cobranças futuras indevidas.")
            );
        } else {
            // Categoria padrão: Seguranca (id 1)
            return Arrays.asList(
                new Pergunta("Phishing é um tipo de golpe para roubar dados?", "sim", "Exato. É uma 'pescaria' de informações confidenciais."),
                new Pergunta("Uma senha forte deve ter pelo menos 12 caracteres?", "sim", "Quanto maior a senha, mais tempo levaria para um computador descobri-la por força bruta."),
                new Pergunta("Usar a mesma senha em todos os sites é uma boa prática?", "nao", "Não! Se um site vazar sua senha, todos os seus outros acessos estarão em risco.")
            );
        }
    }

    public int corrigir(List<String> respostasUsuario, String categoria) {
        List<Pergunta> perguntas = getPerguntas(categoria);
        int pontuacao = 0;
        int numRespostas = Math.min(respostasUsuario.size(), perguntas.size());

        for (int i = 0; i < numRespostas; i++) {
            if (respostasUsuario.get(i) != null && 
                respostasUsuario.get(i).equalsIgnoreCase(perguntas.get(i).getRespostaCorreta())) {
                pontuacao++;
            }
        }
        return pontuacao;
    }
}