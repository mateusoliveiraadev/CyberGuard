package com.cyberguard.cyberguard.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cyberguard.cyberguard.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    
    List<Usuario> findTop10ByOrderByPontuacaoDesc();
}