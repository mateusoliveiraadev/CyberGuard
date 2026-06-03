package com.cyberguard.cyberguard.service;

import com.cyberguard.cyberguard.entity.Usuario;
import com.cyberguard.cyberguard.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList; // Novo import
import java.util.List;      // Novo import
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario cadastrar(Usuario usuario) {
        // Padroniza e tira espaços desnecessários das pontas
        usuario.setEmail(usuario.getEmail().toLowerCase().trim());
        usuario.setNome(usuario.getNome().trim());

        // Cria a "sacola" para guardar os erros
        List<String> erros = new ArrayList<>();

        // Verifica o e-mail
        if (repository.findByEmail(usuario.getEmail()).isPresent()) {
            erros.add("Este e-mail já está cadastrado.");
        }

        // Verifica o nome de usuário
        if (repository.findByNome(usuario.getNome()).isPresent()) {
            erros.add("Este nome de usuário já está em uso.");
        }

        // Se encontrou algum erro (1 ou 2 erros), junta tudo e devolve
        if (!erros.isEmpty()) {
            // Junta as mensagens separadas por um espaço
            String mensagemDeErro = String.join(" ", erros);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, mensagemDeErro);
        }
        
        // Se passou direto, salva com a senha protegida!
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return repository.save(usuario);
    }

    public Usuario login(String email, String senha) {
        Optional<Usuario> user = repository.findByEmail(email.toLowerCase().trim());

        if (user.isEmpty() || !passwordEncoder.matches(senha, user.get().getSenha())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais inválidas.");
        }

        return user.get();
    }

    public Usuario buscarPorEmail(String email) {
        return repository.findByEmail(email.toLowerCase().trim())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));
    }
}