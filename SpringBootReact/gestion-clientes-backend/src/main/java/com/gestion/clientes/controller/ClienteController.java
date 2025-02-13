package com.gestion.clientes.controller;

import com.gestion.clientes.exception.ResourceNotFoundException;
import com.gestion.clientes.model.Cliente;
import com.gestion.clientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/solar/v1/")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;


    @GetMapping("/clientes")
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }


    @PostMapping("/clientes")
    public Cliente guardarCliente(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }


    @GetMapping("/clientes/{id_cliente}")
    public ResponseEntity<Cliente> listarClientePorId(@PathVariable Long id_cliente) {
        Cliente cliente = clienteRepository.findById(id_cliente)
                .orElseThrow(() -> new ResourceNotFoundException("El cliente con ese ID no existe: " + id_cliente));
        return ResponseEntity.ok(cliente);
    }


    @PutMapping("/clientes/{id_cliente}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id_cliente, @RequestBody Cliente clienteRequest) {
        Cliente cliente = clienteRepository.findById(id_cliente)
                .orElseThrow(() -> new ResourceNotFoundException("El cliente con ese ID no existe: " + id_cliente));


        cliente.setNombre(clienteRequest.getNombre());
        cliente.setTelefono(clienteRequest.getTelefono());
        cliente.setCorreo(clienteRequest.getCorreo());
        cliente.setDni(clienteRequest.getDni());
        cliente.setClave(clienteRequest.getClave());
        cliente.setNotificaciones(clienteRequest.getNotificaciones());


        Cliente clienteActualizado = clienteRepository.save(cliente);
        return ResponseEntity.ok(clienteActualizado);
    }


    @DeleteMapping("/clientes/{id_cliente}")
    public ResponseEntity<Map<String, Boolean>> eliminarCliente(@PathVariable Long id_cliente) {
        Cliente cliente = clienteRepository.findById(id_cliente)
                .orElseThrow(() -> new ResourceNotFoundException("El cliente con ese ID no existe: " + id_cliente));

        clienteRepository.delete(cliente);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
