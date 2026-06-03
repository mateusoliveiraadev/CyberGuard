package com.cyberguard.cyberguard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {

    // 1. Configuração para criptografar as senhas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. Configuração para desativar a tela de login padrão e liberar os endpoints
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desativa proteção CSRF para permitir POST/PUT externos
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Libera todas as rotas
            )
            .formLogin(form -> form.disable()) // Desativa formulário de login padrão
            .httpBasic(basic -> basic.disable()); // Desativa autenticação básica

        return http.build();
    }

    // 3. Sua configuração original de CORS (mantida intacta)
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*") // Permite que a Vercel e o localhost acessem o Java
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD")
                        .allowedHeaders("*")
                        .exposedHeaders("*");
            }
        };
    }
}