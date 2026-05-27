package com.cyberguard.cyberguard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.cyberguard.cyberguard.entity")
@EnableJpaRepositories("com.cyberguard.cyberguard.repository")
public class CyberguardApplication {

    public static void main(String[] args) {
        SpringApplication.run(CyberguardApplication.class, args);
    }
}