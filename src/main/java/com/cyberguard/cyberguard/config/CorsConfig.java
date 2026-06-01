package com.cyberguard.cyberguard.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("https://cyber-guard-frontend.vercel.app") 
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD") // Garanta que OPTIONS está aqui
            .allowedHeaders("*")
            .allowCredentials(true)
            .maxAge(3600); // Adicione o tempo de cache para o preflight
}
}