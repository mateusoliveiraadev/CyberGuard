package com.cyberguard.cyberguard.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyberguard.cyberguard.entity.ProgressoAula;
import com.cyberguard.cyberguard.repository.ProgressoAulaRepository;

@RestController
@RequestMapping("/api/progresso")
@CrossOrigin(origins = "*") 
public class ProgressoAulaController {

    private final ProgressoAulaRepository repository;

    public ProgressoAulaController(ProgressoAulaRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/concluir")
    public ResponseEntity<?> marcarComoConcluida(@RequestBody Map<String, String> dados) {
        String email = dados.get("emailUsuario");
        String videoId = dados.get("videoId");

        // Checa se o usuário já tinha concluído essa aula antes
        if (!repository.existsByEmailUsuarioAndVideoId(email, videoId)) {
            ProgressoAula progresso = new ProgressoAula();
            progresso.setEmailUsuario(email);
            progresso.setVideoId(videoId);
            repository.save(progresso);
            return ResponseEntity.ok("Progresso salvo com sucesso!");
        }

        return ResponseEntity.ok("Aula já estava concluída.");
    }
}