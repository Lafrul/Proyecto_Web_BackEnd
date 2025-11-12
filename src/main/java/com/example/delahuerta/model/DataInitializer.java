package com.example.delahuerta.model;

import com.example.delahuerta.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initUsers(UserRepository userRepository,
                                PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User u = new User();
                u.setUsername("admin");
                // IMPORTANTE: encriptar la contraseña porque usas BCrypt en SecurityConfig
                u.setPassword(passwordEncoder.encode("admin123"));
                u.setRole("ADMIN");
                userRepository.save(u);
            }
        };
    }
}
