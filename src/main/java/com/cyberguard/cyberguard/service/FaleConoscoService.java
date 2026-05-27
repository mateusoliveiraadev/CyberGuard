package com.cyberguard.cyberguard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyberguard.cyberguard.entity.FaleConosco;
import com.cyberguard.cyberguard.repository.FaleConoscoRepository;

@Service
public class FaleConoscoService {

    @Autowired
    private FaleConoscoRepository repository;

    public FaleConosco salvarMensagem(
            FaleConosco mensagem
    ) {

        return repository.save(mensagem);
    }
}