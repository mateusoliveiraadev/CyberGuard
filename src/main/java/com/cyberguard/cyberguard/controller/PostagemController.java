package com.cyberguard.cyberguard.controller;

import com.cyberguard.cyberguard.entity.Postagem;
import com.cyberguard.cyberguard.entity.Usuario;
import com.cyberguard.cyberguard.repository.PostagemRepository;
import com.cyberguard.cyberguard.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/postagens")
@CrossOrigin(origins = "*")
public class PostagemController {

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // 🆕 NOVO ENDPOINT: Listar todas as postagens (da mais recente para a mais antiga)
    @GetMapping
    public ResponseEntity<List<Postagem>> listarPostagens() {
        try {
            // Buscando e ordenando decrescentemente pela data de criação
            List<Postagem> postagens = postagemRepository.findAll(Sort.by(Sort.Direction.DESC, "dataCriacao"));
            return ResponseEntity.ok(postagens);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping
    public ResponseEntity<?> criarPostagem(@RequestBody Map<String, Object> payload) {
        try {
            String conteudo = (String) payload.get("conteudo");
            Long autorId = Long.valueOf(payload.get("autorId").toString());

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