package com.cyberguard.cyberguard.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyberguard.cyberguard.entity.Certificado;
import com.cyberguard.cyberguard.entity.ProgressoAula;
import com.cyberguard.cyberguard.entity.Usuario;
import com.cyberguard.cyberguard.repository.CertificadoRepository;
import com.cyberguard.cyberguard.repository.ProgressoAulaRepository;
import com.cyberguard.cyberguard.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/progresso")
@CrossOrigin(origins = "*") 
public class ProgressoAulaController {

    private final ProgressoAulaRepository repository;
    private final CertificadoRepository certificadoRepository;
    private final UsuarioRepository usuarioRepository;

    // Injeção do UsuarioRepository adicionada aqui
    public ProgressoAulaController(ProgressoAulaRepository repository, 
                                   CertificadoRepository certificadoRepository,
                                   UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.certificadoRepository = certificadoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/concluir")
    public ResponseEntity<?> marcarComoConcluida(@RequestBody Map<String, String> dados) {
        String email = dados.get("emailUsuario");
        String videoId = dados.get("videoId");

        // Checa se o usuário já tinha concluído essa aula antes (se não, é visualização única)
        if (!repository.existsByEmailUsuarioAndVideoId(email, videoId)) {
            
            // 1. Salva o progresso da aula
            ProgressoAula progresso = new ProgressoAula();
            progresso.setEmailUsuario(email);
            progresso.setVideoId(videoId);
            repository.save(progresso);

            // 2. Procura o utilizador na base de dados para atualizar os pontos
            Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
            
            if (usuarioOpt.isPresent()) {
                Usuario usuario = usuarioOpt.get();
                
                // Variável para acumular os pontos ganhos nesta requisição (1 ponto pelo vídeo)
                int pontosGanhosNestaAcao = 1; 

                // 3. Regra do Certificado: Verifica se atingiu múltiplo de 5
                long totalVideosAssistidos = repository.countByEmailUsuario(email);
                
                if (totalVideosAssistidos > 0 && totalVideosAssistidos % 5 == 0) {
                    long numeroCertificado = totalVideosAssistidos / 5;
                    String nomeDoCertificado = "Certificado " + numeroCertificado;

                    if (!certificadoRepository.existsByEmailUsuarioAndNome(email, nomeDoCertificado)) {
                        Certificado novoCertificado = new Certificado();
                        novoCertificado.setEmailUsuario(email);
                        novoCertificado.setNome(nomeDoCertificado);
                        certificadoRepository.save(novoCertificado);
                        
                        // O utilizador ganhou um certificado! Adiciona +5 pontos à variável
                        pontosGanhosNestaAcao += 5; 
                    }
                }

                // 4. Soma a pontuação final (1 ponto do vídeo + 5 do certificado, se ganhou) 
                // à pontuação que o utilizador já tinha antes, e guarda.
                usuario.setPontuacao(usuario.getPontuacao() + pontosGanhosNestaAcao);
                usuarioRepository.save(usuario);
            }

            return ResponseEntity.ok("Progresso salvo com sucesso!");
        }

        return ResponseEntity.ok("Aula já estava concluída. Nenhuma pontuação extra adicionada.");
    }
}