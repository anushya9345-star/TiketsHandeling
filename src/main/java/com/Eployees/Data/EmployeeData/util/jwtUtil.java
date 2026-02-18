package com.Eployees.Data.EmployeeData.util;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import static javax.crypto.Cipher.SECRET_KEY;

@Component
public class jwtUtil {

    private final String secret = "This is secret String type data that i have created";
    private final long Expiration = 1000 * 60 * 60;
    private final Key securityKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

    public String generateToken (String userId)
    {
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date (System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Expiration))
                .signWith(securityKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUser (String token)
    {
        return Jwts.parser()
                .setSigningKey(securityKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public boolean validateJwtToken (String token)
    {
        try {
             extractUser(token);
            return true;
        }

        catch (JwtException exception )
        {
            return false;
        }
    }
}
