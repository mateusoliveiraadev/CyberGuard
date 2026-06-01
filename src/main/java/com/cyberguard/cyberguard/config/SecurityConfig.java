package com.cyberguard.cyberguard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors() // <--- ISSO É O QUE VOCÊ PRECISA: Ativa o suporte ao CORS que você configurou no CorsConfig
            .and()
            .csrf().disable() // Desabilita CSRF para permitir POSTs do seu React
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Libera todos os endpoints (ajuste conforme necessário)
            );
        
        return http.build();
    }
}