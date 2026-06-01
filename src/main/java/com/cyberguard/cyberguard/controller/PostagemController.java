package com.cyberguard.cyberguard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyberguard.cyberguard.entity.Postagem;
import com.cyberguard.cyberguard.service.PostagemService;

@RestController
@RequestMapping("/api/postagens")
// 👇 APAGAMOS A LINHA DO @CrossOrigin DAQUI! 👇
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