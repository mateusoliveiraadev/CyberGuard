package com.cyberguard.cyberguard.controller;

import com.cyberguard.cyberguard.entity.Postagem;
import com.cyberguard.cyberguard.service.PostagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/postagens")
@CrossOrigin(origins = "http://localhost:5173")
public class PostagemController {

    private final PostagemService service;

    @Autowired
    public PostagemController(PostagemService service) {
        this.service = service;
    }

    // 🔍 1. ROTA PARA LISTAR TODAS AS POSTAGENS (FÓRUM/COMUNIDADE)
    @GetMapping
    public ResponseEntity<List<Postagem>> listarTodas() {
        List<Postagem> postagens = service.listarTodas();
        return ResponseEntity.ok(postagens);
    }

    // 💾 2. ROTA PARA CRIAR UMA NOVA POSTAGEM
    @PostMapping
    public ResponseEntity<Postagem> criarPostagem(@RequestBody Postagem postagem) {
        Postagem novaPostagem = service.salvar(postagem);
        return ResponseEntity.ok(novaPostagem);
    }

    // 📝 3. ROTA PARA EDITAR UMA POSTAGEM EXISTENTE
    @PutMapping("/{id}")
    public ResponseEntity<?> editarPostagem(@PathVariable Long id, @RequestBody Postagem postagemAtualizada) {
        try {
            Postagem salva = service.atualizar(id, postagemAtualizada);
            return ResponseEntity.ok(salva);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ❌ 4. ROTA PARA DELETAR UMA POSTAGEM
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPostagem(@PathVariable Long id) {
        try {
            service.deletar(id);
            return ResponseEntity.ok().body("Postagem deletada com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}