package com.gestion.clientes.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenerarHash {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String contraseñaPlana = "1990";
        String hash = passwordEncoder.encode(contraseñaPlana);
        System.out.println("Hash de la contraseña: " + hash);
    }
}
