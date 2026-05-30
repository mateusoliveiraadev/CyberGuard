package com.cyberguard.cyberguard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cyberguard.cyberguard.entity.Certificado;

@Repository
public interface CertificadoRepository extends JpaRepository<Certificado, Long> {
    List<Certificado> findByEmailUsuario(String emailUsuario);
    boolean existsByEmailUsuarioAndNome(String emailUsuario, String nome);
}