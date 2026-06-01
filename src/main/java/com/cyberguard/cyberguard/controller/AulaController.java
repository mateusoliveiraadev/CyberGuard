package com.cyberguard.cyberguard.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/aulas")
// 👇 APAGAMOS A LINHA DO @CrossOrigin DAQUI! 👇
public class AulaController {

    // Puxa a chave do application.properties
    @Value("${youtube.api.key}")
    private String apiKey;

    // Puxa o ID da playlist do application.properties
    @Value("${youtube.playlist.id}")
    private String playlistId;

    @GetMapping
    public ResponseEntity<String> listarAulas(
            @RequestParam(defaultValue = "") String pageToken,
            @RequestParam(defaultValue = "10") int maxResults) {
        
        try {
            // 1. Monta a URL oficial da API do YouTube v3
            String url = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet,contentDetails&playlistId=" 
                         + playlistId + "&maxResults=" + maxResults + "&key=" + apiKey;
            
            // 2. Se o React pedir a página 2, 3, etc., adicionamos o token na URL
            if (!pageToken.isEmpty()) {
                url += "&pageToken=" + pageToken;
            }

            // 3. Faz a requisição HTTP para o YouTube
            RestTemplate restTemplate = new RestTemplate();
            String respostaJsonDoYoutube = restTemplate.getForObject(url, String.class);
            
            // 4. Devolve o texto exato do YouTube para o React
            return ResponseEntity.ok(respostaJsonDoYoutube);

        } catch (Exception e) {
            System.err.println("Erro ao buscar no YouTube: " + e.getMessage());
            return ResponseEntity.status(500).body("{\"error\": \"Não foi possível carregar os vídeos do YouTube no momento.\"}");
        }
    }
}