package com.cyberguard.cyberguard.service;

import com.cyberguard.cyberguard.entity.Denuncia;
import com.cyberguard.cyberguard.repository.DenunciaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DenunciaService {

    private final DenunciaRepository repository;

    public DenunciaService(DenunciaRepository repository) {
        this.repository = repository;
    }

    public List<Denuncia> listarTodas() {
        return repository.findAll();
    }

    public Denuncia salvar(Denuncia denuncia) {
        return repository.save(denuncia);
    }
}