package com.example.parcial2.config;

import com.example.parcial2.entity.*;
import com.example.parcial2.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
        CommandLineRunner seedData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!userRepository.existsByEmail("admin@bookstore.com")) {
                userRepository.save(User.builder()
                        .fullName("Admin Bookstore")
                        .email("admin@bookstore.com")
                        .password(passwordEncoder.encode("Admin123*"))
                        .role(Role.ROLE_ADMIN)
                        .build());
            }

            if (!userRepository.existsByEmail("vendedor@bookstore.com")) {
                userRepository.save(User.builder()
                        .fullName("Vendedor Demo")
                        .email("vendedor@bookstore.com")
                        .password(passwordEncoder.encode("Vendor123*"))
                        .role(Role.ROLE_VENDEDOR)
                        .build());
            }

            if (!userRepository.existsByEmail("lector@bookstore.com")) {
                userRepository.save(User.builder()
                        .fullName("Lector Demo")
                        .email("lector@bookstore.com")
                        .password(passwordEncoder.encode("Reader123*"))
                        .role(Role.ROLE_LECTOR)
                        .build());
            }
        };
    }
}
