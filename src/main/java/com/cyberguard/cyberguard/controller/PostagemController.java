package com.cyberguard.cyberguard.controller;

import com.cyberguard.cyberguard.entity.Postagem;
import com.cyberguard.cyberguard.entity.Usuario;
import com.cyberguard.cyberguard.repository.PostagemRepository;
import com.cyberguard.cyberguard.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/postagens")
@CrossOrigin(origins = "*") // Permite que o frontend acesse sem problemas de CORS
public class PostagemController {

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Endpoint focado puramente em salvar uma nova postagem
    @PostMapping
    public ResponseEntity<?> criarPostagem(@RequestBody Map<String, Object> payload) {
        try {
            String conteudo = (String) payload.get("conteudo");
            // Para simplificar esta etapa, pegamos o ID do autor enviado pelo front
            Long autorId = Long.valueOf(payload.get("autorId").toString());

            // Verifica se o usuário que está postando realmente existe no banco
            Usuario autor = usuarioRepository.findById(autorId)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            Postagem novaPostagem = new Postagem();
            novaPostagem.setConteudo(conteudo);
            novaPostagem.setAutor(autor);

            Postagem postagemSalva = postagemRepository.save(novaPostagem);

            return ResponseEntity.ok(postagemSalva);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao criar postagem: " + e.getMessage());
        }
    }
}