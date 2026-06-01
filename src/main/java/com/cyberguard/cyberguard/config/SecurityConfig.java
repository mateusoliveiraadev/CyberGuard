@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .cors().configurationSource(request -> {
            var cors = new org.springframework.web.cors.CorsConfiguration();
            cors.setAllowedOrigins(java.util.List.of("https://cyber-guard-frontend.vercel.app"));
            cors.setAllowedMethods(java.util.List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
            cors.setAllowedHeaders(java.util.List.of("*"));
            cors.setAllowCredentials(true);
            return cors;
        })
        .and()
        .csrf().disable()
        .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
    return http.build();
}