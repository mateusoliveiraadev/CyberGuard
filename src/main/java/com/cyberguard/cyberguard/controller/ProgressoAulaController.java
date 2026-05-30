package com.cyberguard.cyberguard.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyberguard.cyberguard.entity.Certificado;
import com.cyberguard.cyberguard.entity.ProgressoAula;
import com.cyberguard.cyberguard.repository.CertificadoRepository;
import com.cyberguard.cyberguard.repository.ProgressoAulaRepository;

@RestController
@RequestMapping("/api/progresso")
@CrossOrigin(origins = "*") 
public class ProgressoAulaController {

    private final ProgressoAulaRepository repository;
    private final CertificadoRepository certificadoRepository;

    // Injeção de dependências dos dois repositórios
    public ProgressoAulaController(ProgressoAulaRepository repository, CertificadoRepository certificadoRepository) {
        this.repository = repository;
        this.certificadoRepository = certificadoRepository;
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

            // Regra de Negócio: Emitir certificado a cada 5 vídeos
            long totalVideosAssistidos = repository.countByEmailUsuario(email);
            
            if (totalVideosAssistidos > 0 && totalVideosAssistidos % 5 == 0) {
                long numeroCertificado = totalVideosAssistidos / 5;
                String nomeDoCertificado = "Certificado " + numeroCertificado;

                // Verifica se o certificado já existe para evitar duplicados
                if (!certificadoRepository.existsByEmailUsuarioAndNome(email, nomeDoCertificado)) {
                    Certificado novoCertificado = new Certificado();
                    novoCertificado.setEmailUsuario(email);
                    novoCertificado.setNome(nomeDoCertificado);
                    certificadoRepository.save(novoCertificado);
                }
            }

            return ResponseEntity.ok("Progresso salvo com sucesso!");
        }

        return ResponseEntity.ok("Aula já estava concluída.");
    }
}