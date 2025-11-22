package com.tastytown.backend.security.jwt;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

// import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.tastytown.backend.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

    // NOTE: keep this as Base64 string or change getKey() if you store raw bytes.
    private static final String JWT_SECRET = "X3o2Fjptt7gV1G2cYqU1D0P1YHklwJdf3Df7nGVQ8zM=";

    private SecretKey getKey() {
        System.out.println(JWT_SECRET);
        byte[] keyBytes = Base64.getDecoder().decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String generateAccessToken(User user, String userRole) {
        Instant now = Instant.now();

        return Jwts.builder()
                .claims()
                .subject(user.getUserEmail()) // subject = username/email
                .add("role", userRole) // add role claim
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(15, ChronoUnit.MINUTES)))
                .and()
                .signWith(getKey(), Jwts.SIG.HS256)
                .compact();
    }

    public String generateRefreshToken(User user) {
        Instant now = Instant.now();

        return Jwts.builder()
                .claims()
                .subject(user.getUserEmail())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(7, ChronoUnit.DAYS)))
                .and()
                .signWith(getKey(), Jwts.SIG.HS256)
                .compact();
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public String getUserRole(String token) {
        return extractClaims(token).get("role", String.class);
    }

    private boolean isTokenExpired(String token) {
        Date exp = extractClaims(token).getExpiration();
        return exp.before(new Date());
    }

    // validate by username
    public boolean validateToken(String token, String username) {
        try {
            String tokenUsername = getUsername(token);
            return tokenUsername.equals(username) && !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }
}
