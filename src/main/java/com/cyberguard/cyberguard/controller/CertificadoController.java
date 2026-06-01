package com.cyberguard.cyberguard.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyberguard.cyberguard.entity.Certificado;
import com.cyberguard.cyberguard.repository.CertificadoRepository;

@RestController
@RequestMapping("/api/certificados")
// 👇 APAGAMOS A LINHA DO @CrossOrigin DAQUI! 👇
public class CertificadoController {

    private final CertificadoRepository repository;

    public CertificadoController(CertificadoRepository repository) {
        this.repository = repository;
    }

    // Endpoint para ir buscar a lista de certificados de um utilizador específico
    @GetMapping("/{email}")
    public ResponseEntity<List<Certificado>> listarCertificados(@PathVariable String email) {
        List<Certificado> certificados = repository.findByEmailUsuario(email);
        return ResponseEntity.ok(certificados);
    }
}