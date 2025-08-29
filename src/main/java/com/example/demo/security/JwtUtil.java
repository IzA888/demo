package com.example.demo.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.demo.model.UserModel;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private final String secret= "chave_super_super_super_super_secreta"; 
    private final Key secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)); 
    private final Long expirationTime = 86400000L; // 1 day in milliseconds

    // Implement JWT utility methods here, such as token generation and validation
    public String generateToken(UserModel userModel) {
        // Logic to generate JWT token
        return Jwts.builder()
                .setSubject(userModel.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                //.signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean validateToken(String token, UserModel userModel) {
        // Logic to validate JWT token
        String username = getUsernameFromToken(token);
        return (username.equals(userModel.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        // Logic to check if JWT token is expired
        Date expiration = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date()); // Check if expiration date is before current date
    }

    public String getUsernameFromToken(String token) {
        // Logic to extract username from JWT token
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public UserDetails getUserDetailsFromToken(String token) {
        // Logic to get UserDetails from JWT token
        return User.withUsername("username").password("password").authorities("ROLE_USER").build(); 
    }


}