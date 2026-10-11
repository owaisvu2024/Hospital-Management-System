package com.Hospital_System.demo.Security;

import com.Hospital_System.demo.Entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private final SecretKey secretKey = Keys.hmacShaKeyFor(
            "trwuweu83799783hrjhjbkhwqgduueidhbj".getBytes()
    );


    public String generateToken(String userName, Set<Role> roles) {

        String rolesStr = roles.stream()
                .map(role -> role.name())
                .collect(Collectors.joining(","));


        return Jwts.builder()
                .subject(userName)
                .claim("roles", rolesStr)
                .issuedAt(new Date())
                .signWith(secretKey)
                .expiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .compact();

    }

    public boolean tokenIsValid(String token) {
        try {
            Jwts.parser()

                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
            return true;

        } catch (Exception e) {
            return false;

        }

    }

    public String extractsUsername(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();

    }

    public String extractsRole(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.get("roles", String.class);


    }

}
