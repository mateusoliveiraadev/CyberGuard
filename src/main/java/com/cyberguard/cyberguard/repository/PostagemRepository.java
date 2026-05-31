package com.cyberguard.cyberguard.repository;

import com.cyberguard.cyberguard.entity.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {
    // Herdando o JpaRepository, já temos o método .save() pronto para uso!
}