package com.example.alloservice.Security;


import com.example.alloservice.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtExpiration;


    // Token Generation

    public String generateToken(User user){
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("role" , user.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSigningKey())
                .compact();
    }

    // Claims Extraction
    public String extractEmail(String token){
        return extractClaim(token , Claims::getSubject);
    }

    public String extractRole(String token){
        return extractClaim(token, claims -> claims.get("role" , String.class));
    }

    private <T> T extractClaim(String token, Function<Claims , T> claimsResolver) {
        final Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claimsResolver.apply(claims);
    }


    // Token Validation
    public boolean isTokenValid(String token , UserDetails userDetails){
        final String email = extractEmail(token);
        return email.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token , Claims::getExpiration).before(new Date());
    }

    // Key

    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

}
