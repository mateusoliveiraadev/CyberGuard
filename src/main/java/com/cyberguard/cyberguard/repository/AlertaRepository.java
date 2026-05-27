package com.cyberguard.cyberguard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cyberguard.cyberguard.entity.Alerta;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long> {
}