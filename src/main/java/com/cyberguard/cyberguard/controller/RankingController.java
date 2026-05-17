package com.cyberguard.cyberguard.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyberguard.cyberguard.entity.RankingDTO;
import com.cyberguard.cyberguard.entity.Usuario;
import com.cyberguard.cyberguard.repository.UsuarioRepository;

@RestController
@RequestMapping("/ranking")
@CrossOrigin(origins = "http://localhost:5173") // Libera o acesso para o seu React
public class RankingController {

    private final UsuarioRepository usuarioRepository;

    // O Spring injeta o seu UsuarioRepository aqui automaticamente
    public RankingController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<RankingDTO> obterRanking() {
        List<Usuario> topUsuarios = usuarioRepository.findAll(); 

        return topUsuarios.stream()
                .sorted((u1, u2) -> Integer.compare(u2.getPontuacao(), u1.getPontuacao())) // Ordena do maior para o menor
                .limit(10) // Pega apenas os 10 melhores
                .map(u -> new RankingDTO(u.getNome(), u.getPontuacao()))
                .collect(Collectors.toList());
    }
}