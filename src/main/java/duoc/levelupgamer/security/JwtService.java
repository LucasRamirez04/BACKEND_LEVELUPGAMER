package duoc.levelupgamer.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final SecretKey key;
    private final long expiracionMinutos;

    public JwtService(@Value("${jwt.secret}") String secret,
                      @Value("${jwt.expiration-minutes}") long expiracionMinutos) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expiracionMinutos = expiracionMinutos;
    }

    // Actualizado para recibir correo y rol
    public String generarToken(String username, String rol){
        Date ahora = new Date();
        Date expiracion = new Date(ahora.getTime() + getExpiracionSegundos() * 1000);
        return Jwts.builder()
                .subject(username)
                .claim("rol", rol)
                .issuedAt(ahora)
                .expiration(expiracion)
                .signWith(key)
                .compact();
    }

    // Devuelve el username (correo) si el token es válido y no ha expirado, si no null
    public String validarToken(String token){
        try {
            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        } catch (JwtException | IllegalArgumentException e) {
            return null;
        }
    }

    // Nuevo método para que JwtFilter pueda leer el rol desde el token
    public String extraerRol(String token){
        try {
            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .get("rol", String.class);
        } catch (JwtException | IllegalArgumentException e) {
            return null;
        }
    }

    public long getExpiracionSegundos(){
        return expiracionMinutos * 60;
    }

}