package com.cyberguard.cyberguard.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/noticias")
// 👇 APAGAMOS A LINHA DO @CrossOrigin DAQUI! 👇
public class NoticiaController {

    @Value("${api.gnews.key}")
    private String gnewsApiKey;

    @GetMapping
    public ResponseEntity<String> buscarNoticias() {
        try {
            // 1. Isto vai imprimir no terminal para vermos se a chave está a carregar corretamente do .env
            System.out.println("Tentando buscar notícias... A chave usada é: " + gnewsApiKey);

            // 2. Mudei o "%20" para "+" porque às vezes o RestTemplate do Java baralha-se com o %
            String url = "https://gnews.io/api/v4/search?q=golpes+digitais&lang=pt&country=br&max=5&apikey=" + gnewsApiKey;
            
            RestTemplate restTemplate = new RestTemplate();
            String respostaJson = restTemplate.getForObject(url, String.class);
            return ResponseEntity.ok(respostaJson);
            
        } catch (Exception e) {
            // 3. Isto vai imprimir O VERDADEIRO ERRO em texto vermelho no seu terminal do VS Code!
            e.printStackTrace(); 
            return ResponseEntity.status(500).body("{\"error\": \"Erro ao buscar notícias: " + e.getMessage() + "\"}");
        }
    }
}