package com.cyberguard.cyberguard.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/aulas")
public class AulaController {

    // Método para buscar a chave do ambiente (Render)
    private String getApiKey() {
        return System.getenv("YOUTUBE_API_KEY");
    }

    // Método para buscar o ID da playlist do ambiente (Render)
    private String getPlaylistId() {
        return System.getenv("YOUTUBE_PLAYLIST_ID");
    }

    @GetMapping
    public ResponseEntity<String> listarAulas(
            @RequestParam(defaultValue = "") String pageToken,
            @RequestParam(defaultValue = "10") int maxResults) {

        String apiKey = getApiKey();
        String playlistId = getPlaylistId();

        // Segurança: Verifica se as variáveis foram carregadas
        if (apiKey == null || playlistId == null) {
            System.err.println("Erro: Variáveis de ambiente YOUTUBE_API_KEY ou YOUTUBE_PLAYLIST_ID não configuradas.");
            return ResponseEntity.status(500).body("{\"error\": \"Configuração de API ausente no servidor.\"}");
        }

        try {
            // 1. Monta a URL oficial da API do YouTube v3
            String url = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet,contentDetails&playlistId="
                    + playlistId + "&maxResults=" + maxResults + "&key=" + apiKey;

            // 2. Adiciona o token de paginação se necessário
            if (pageToken != null && !pageToken.isEmpty()) {
                url += "&pageToken=" + pageToken;
            }

            // 3. Faz a requisição HTTP para o YouTube
            RestTemplate restTemplate = new RestTemplate();
            String respostaJsonDoYoutube = restTemplate.getForObject(url, String.class);

            // 4. Devolve a resposta do YouTube para o React
            return ResponseEntity.ok(respostaJsonDoYoutube);

        } catch (Exception e) {
            System.err.println("Erro ao buscar no YouTube: " + e.getMessage());
            return ResponseEntity.status(500)
                    .body("{\"error\": \"Não foi possível carregar os vídeos do YouTube no momento.\"}");
        }
    }
}