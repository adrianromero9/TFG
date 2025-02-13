/*package com.gestion.clientes.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public JwtFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            System.out.println("🔹 Token recibido: " + token);

            String[] parts = jwtUtil.extractNombreDni(token);

            if (parts.length == 2) {
                String nombre = parts[0];
                String dni = parts[1];

                System.out.println("Nombre extraído del token: " + nombre);
                System.out.println("DNI extraído del token: " + dni);

                if (jwtUtil.validateToken(token, nombre, dni)) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(nombre + ":" + dni);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    System.out.println("Token válido, usuario autenticado.");
                } else {
                    System.out.println("Token inválido.");
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido.");
                    return;
                }
            } else {
                System.out.println("Error al extraer datos del token.");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido.");
                return;
            }
        } else {
            System.out.println("No se recibió token en la petición.");
        }

        filterChain.doFilter(request, response);
    }

}*/