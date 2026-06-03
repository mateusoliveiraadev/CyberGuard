package com.cyberguard.cyberguard.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cyberguard.cyberguard.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    
    // 1. Nova função para o Spring Boot procurar se o nome já existe
    Optional<Usuario> findByNome(String nome); 
    
    List<Usuario> findTop10ByOrderByPontuacaoDesc();
}