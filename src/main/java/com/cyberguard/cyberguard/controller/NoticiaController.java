package com.cyberguard.cyberguard.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/noticias")
@CrossOrigin(origins = "*")
public class NoticiaController {

    @Value("${api.gnews.key}")
    private String gnewsApiKey;

    @GetMapping
    public ResponseEntity<String> buscarNoticias() {
        try {
            String url = "https://gnews.io/api/v4/search?q=golpes%20digitais&lang=pt&country=br&max=5&apikey=" + gnewsApiKey;
            RestTemplate restTemplate = new RestTemplate();
            String respostaJson = restTemplate.getForObject(url, String.class);
            return ResponseEntity.ok(respostaJson); // Devolve o JSON exato da GNews para o React
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"error\": \"Erro ao buscar notícias\"}");
        }
    }
}