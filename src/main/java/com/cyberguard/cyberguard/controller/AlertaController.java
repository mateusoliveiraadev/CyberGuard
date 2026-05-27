package com.cyberguard.cyberguard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyberguard.cyberguard.entity.Alerta;
import com.cyberguard.cyberguard.repository.AlertaRepository;

@RestController
@RequestMapping("/api/alertas")
@CrossOrigin(origins = "*") // Permite o React puxar os dados
public class AlertaController {

    @Autowired
    private AlertaRepository alertaRepository;

    @GetMapping
    public List<Alerta> listarAlertas() {
        return alertaRepository.findAll();
    }
}