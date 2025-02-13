package com.gestion.clientes.controller;

import com.gestion.clientes.model.Cliente;
import com.gestion.clientes.repository.ClienteRepository;
//import com.gestion.clientes.security.JwtResponse;
//import com.gestion.clientes.security.JwtUtil;
import com.gestion.clientes.security.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final ClienteRepository clienteRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    //private final JwtUtil jwtUtil;

    public AuthController(ClienteRepository clienteRepository, BCryptPasswordEncoder passwordEncoder, AuthenticationManager authenticationManager/*, JwtUtil jwtUtil*/) {
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        //this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public String register(@RequestBody Cliente cliente) {
        cliente.setClave(passwordEncoder.encode(cliente.getClave()));
        clienteRepository.save(cliente);
        return "Cliente registrado exitosamente.";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<Cliente> clienteOpt = clienteRepository.findByNombreAndDni(loginRequest.getNombre(), loginRequest.getDni());

        if (clienteOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Error: Cliente no encontrado.");
        }

        Cliente cliente = clienteOpt.get();

        System.out.println("Clave almacenada en BD: " + cliente.getClave());
        System.out.println("Clave ingresada: " + loginRequest.getClave());

        if (!passwordEncoder.matches(loginRequest.getClave(), cliente.getClave())) { 
            System.out.println("Error: Clave incorrecta.");
            return ResponseEntity.badRequest().body("Error: Credenciales incorrectas.");
        }

        return ResponseEntity.ok("Inicio de sesión exitoso.");
    }


}
