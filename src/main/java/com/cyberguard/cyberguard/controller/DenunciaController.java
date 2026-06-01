package com.cyberguard.cyberguard.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyberguard.cyberguard.entity.Denuncia;
import com.cyberguard.cyberguard.service.DenunciaService;

@RestController
@RequestMapping("/denuncias")
// 👇 APAGAMOS A LINHA DO @CrossOrigin DAQUI! 👇
public class DenunciaController {

    private final DenunciaService service;

    public DenunciaController(DenunciaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Denuncia> listar() {
        return service.listarTodas();
    }

    @PostMapping
    public Denuncia criar(@RequestBody Denuncia denuncia) {
        return service.salvar(denuncia);
    }
}