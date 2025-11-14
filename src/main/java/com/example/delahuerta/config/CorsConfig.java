package com.example.delahuerta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Usar allowedOriginPatterns en lugar de allowedOrigins para mejor compatibilidad
        configuration.setAllowedOriginPatterns(Arrays.asList(
            "https://juanulloa2050.github.io",
            "https://*.github.io",
            "http://localhost:*",
            "http://127.0.0.1:*"
        ));
        
        // Métodos HTTP permitidos
        configuration.setAllowedMethods(Arrays.asList(
            "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS", "HEAD"
        ));
        
        // Headers permitidos (acepta cualquier header)
        configuration.setAllowedHeaders(Arrays.asList("*"));
        
        // Headers expuestos al cliente
        configuration.setExposedHeaders(Arrays.asList(
            "Authorization", 
            "Content-Type",
            "X-Requested-With",
            "accept",
            "Origin",
            "Access-Control-Request-Method",
            "Access-Control-Request-Headers"
        ));
        
        // Permitir credenciales
        configuration.setAllowCredentials(true);
        
        // Cache de preflight por 1 hora
        configuration.setMaxAge(3600L);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }
}