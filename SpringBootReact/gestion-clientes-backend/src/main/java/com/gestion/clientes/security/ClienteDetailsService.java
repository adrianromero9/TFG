package com.gestion.clientes.security;

import com.gestion.clientes.model.Cliente;
import com.gestion.clientes.repository.ClienteRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClienteDetailsService implements UserDetailsService {

    private final ClienteRepository clienteRepository;

    public ClienteDetailsService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByNombre(username)
                .orElseThrow(() -> new UsernameNotFoundException("Cliente no encontrado con nombre: " + username));
        return new ClienteDetails(cliente);
    }

}
