package com.example.parcial2.repository;

import com.example.parcial2.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByCorreoIgnoreCase(String correo);
    Optional<Cliente> findByCorreoIgnoreCase(String correo);
}
