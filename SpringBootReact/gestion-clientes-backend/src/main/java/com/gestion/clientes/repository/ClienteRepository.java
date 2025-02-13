package com.gestion.clientes.repository;

import com.gestion.clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCorreo(String correo);
    Optional<Cliente> findByNombreAndDni(String nombre, String dni);
    Optional<Cliente> findByNombre(String nombre);
}
