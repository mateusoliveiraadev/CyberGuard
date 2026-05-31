package com.cyberguard.cyberguard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cyberguard.cyberguard.entity.ProgressoAula;

@Repository
public interface ProgressoAulaRepository extends JpaRepository<ProgressoAula, Long> {
    boolean existsByEmailUsuarioAndVideoId(String emailUsuario, String videoId);
    
    long countByEmailUsuario(String emailUsuario);
}