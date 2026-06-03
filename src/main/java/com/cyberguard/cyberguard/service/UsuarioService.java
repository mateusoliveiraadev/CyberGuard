package com.cyberguard.cyberguard.service;

import com.cyberguard.cyberguard.entity.Usuario;
import com.cyberguard.cyberguard.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder; // Importação adicionada
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder; // Adicionado como dependência

    // O construtor foi atualizado para receber o PasswordEncoder
    public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario cadastrar(Usuario usuario) {
        if (repository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }
        
        // Criptografa a senha antes de salvar no banco de dados
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        
        return repository.save(usuario);
    }

    public Usuario login(String email, String senha) {
        Optional<Usuario> user = repository.findByEmail(email);

        // Substituímos o .equals() pelo .matches() para comparar a senha digitada com o Hash salvo
        if (user.isEmpty() || !passwordEncoder.matches(senha, user.get().getSenha())) {
            throw new RuntimeException("Credenciais inválidas");
        }

        return user.get();
    }

    public Usuario buscarPorEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o email: " + email));
    }
}