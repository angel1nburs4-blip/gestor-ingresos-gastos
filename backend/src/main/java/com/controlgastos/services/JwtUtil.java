package com.controlgastos.services;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;
import java.util.Date;

/**
 * Utilidad para generación y validación de tokens JWT.
 *
 * <p>Permite emitir tokens firmados con un secreto compartido y validar su autenticidad.</p>
 *
 * <p><strong>Advertencias:</strong></p>
 * <ul>
 *   <li>La clave secreta debe almacenarse de forma segura (e.g., en variables de entorno).</li>
 *   <li>La expiración actual es de 3600 ms (~3.6 segundos), lo cual es inusualmente corta.</li>
 *   <li>En producción, se recomienda usar {@link io.jsonwebtoken.security.Keys} y {@code HS512}.</li>
 * </ul>
 */
@Service
public class JwtUtil {

    private String secretKey = "MySecretKey"; // ⚠️ Reemplazar por variable de entorno en producción

    /**
     * Genera un token JWT para el usuario especificado.
     *
     * @param username nombre de usuario que se incluirá como sujeto del token.
     * @return token JWT firmado.
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600)) // ⚠️ Solo 3.6 segundos
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /**
     * Valida un token JWT y extrae el nombre de usuario si es válido.
     *
     * @param token JWT a validar.
     * @return nombre de usuario si el token es válido, {@code null} si es inválido o expirado.
     */
    public String validateToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}
