package com.cyberguard.cyberguard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyberguard.cyberguard.entity.Alerta;
import com.cyberguard.cyberguard.repository.AlertaRepository;

@RestController
@RequestMapping("/api/alertas")
@CrossOrigin(origins = "*")
public class AlertaController {

    @Autowired
    private AlertaRepository alertaRepository;

    // Retorna todos os alertas (para a tela principal)
    @GetMapping
    public List<Alerta> listarAlertas() {
        return alertaRepository.findAll();
    }

    // NOVO: Retorna apenas UM alerta pelo ID (para a tela de detalhes)
    @GetMapping("/{id}")
    public ResponseEntity<Alerta> buscarAlertaPorId(@PathVariable Long id) {
        return alertaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}