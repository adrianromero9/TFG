/*package com.gestion.clientes.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "EstaEsUnaClaveSuperSecretaParaElJWTCon32Bytes";
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(String nombre, String dni) {
        return Jwts.builder()
                .setSubject(nombre + ":" + dni)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token, String nombre, String dni) {
        try {
            String[] datos = extractNombreDni(token);
            return datos[0].equals(nombre) && datos[1].equals(dni) && !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }


    public String[] extractNombreDni(String token) {
        String subject = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return subject.split(":"); // Dividimos para obtener [nombre, dni]
    }


    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
}*/
