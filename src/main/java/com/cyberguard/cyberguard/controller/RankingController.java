package com.cyberguard.cyberguard.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam; // <-- IMPORTANTE ADICIONAR ISSO
import org.springframework.web.bind.annotation.RestController;

import com.cyberguard.cyberguard.entity.RankingDTO;
import com.cyberguard.cyberguard.entity.Usuario;
import com.cyberguard.cyberguard.repository.UsuarioRepository;

@RestController
@RequestMapping("/ranking")
@CrossOrigin(origins = "http://localhost:5173") 
public class RankingController {

    private final UsuarioRepository usuarioRepository;

    public RankingController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // 1. ESTA É A ROTA QUE VOCÊ JÁ TINHA (Para a tela de Ranking Top 10)
    @GetMapping
    public List<RankingDTO> obterRanking() {
        List<Usuario> topUsuarios = usuarioRepository.findAll(); 

        return topUsuarios.stream()
                .sorted((u1, u2) -> Integer.compare(u2.getPontuacao(), u1.getPontuacao()))
                .limit(10)
                .map(u -> new RankingDTO(u.getNome(), u.getPontuacao()))
                .collect(Collectors.toList());
    }

    // 2. === NOVA ROTA (Para a tela de Perfil) ===
    // Ela recebe o e-mail do usuário e devolve apenas a pontuação exata dele
    @GetMapping("/pontuacao")
    public Integer obterPontuacaoUsuario(@RequestParam String email) {
        List<Usuario> todosUsuarios = usuarioRepository.findAll();
        
        return todosUsuarios.stream()
                .filter(u -> u.getEmail().equals(email)) // Procura quem tem esse e-mail
                .map(Usuario::getPontuacao) // Pega a pontuação dessa pessoa
                .findFirst()
                .orElse(0); // Se por algum motivo não achar, devolve 0
    }
}