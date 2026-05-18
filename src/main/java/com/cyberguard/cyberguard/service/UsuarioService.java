package com.cyberguard.cyberguard.service;

import com.cyberguard.cyberguard.entity.Usuario;
import com.cyberguard.cyberguard.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario cadastrar(Usuario usuario) {
        if (repository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }
        return repository.save(usuario);
    }

    public Usuario login(String email, String senha) {
        Optional<Usuario> user = repository.findByEmail(email);

        if (user.isEmpty() || !user.get().getSenha().equals(senha)) {
            throw new RuntimeException("Credenciais inválidas");
        }

        return user.get();
    }
}