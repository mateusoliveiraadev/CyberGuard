package com.cyberguard.cyberguard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyberguard.cyberguard.entity.FaleConosco;
import com.cyberguard.cyberguard.service.FaleConoscoService;

// 👇 APAGAMOS A LINHA DO @CrossOrigin DAQUI! 👇
@RestController
@RequestMapping("/faleconosco")
public class FaleConoscoController {

    @Autowired
    private FaleConoscoService service;

    @PostMapping
    public ResponseEntity<FaleConosco> enviarMensagem(
            @RequestBody FaleConosco mensagem
    ) {

        FaleConosco novaMensagem =
                service.salvarMensagem(mensagem);

        return ResponseEntity.ok(novaMensagem);
    }
}