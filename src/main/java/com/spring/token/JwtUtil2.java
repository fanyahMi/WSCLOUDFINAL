package com.spring.token;

import com.spring.models.Utilisateur;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil2 {
    private long JWT_EXPIRATION_MINUTES = 180;

    public Map<String, Object> generateToken(Utilisateur utilisateur) {
        Date now = new Date();
        long jwtExpirationInMs = TimeUnit.MINUTES.toMillis(JWT_EXPIRATION_MINUTES);
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
        String cle = generateSecretKey();
        Claims claims = Jwts.claims().setSubject(Long.toString(utilisateur.getId_utilisateur()));
        claims.put("role", utilisateur.getRoles());
        claims.put("idUtilisateur", utilisateur.getId_utilisateur());
        claims.put("nomPrenom", (utilisateur.getNom() + "  " + utilisateur.getPrenom()));
        if (utilisateur.getRoles() == 10) {
            claims.put("admin", true);
        } else {
            claims.put("user", true);
        }
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, cle)
                .compact();
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("cle", cle);
        response.put("date", now);
        response.put("expirer", expiryDate);
        return response;
    }

    public Claims extractClaims(Token token) {
        return Jwts.parser().setSigningKey(token.getCle()).parseClaimsJws(token.getToken()).getBody();
    }

    private static String generateSecretKey() {
        byte[] keyBytes = Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded();
        return Base64.getEncoder().encodeToString(keyBytes);
    }

    public boolean isTokenExpired(Token token) {
        Date expirationDate = extractClaims(token).getExpiration();
        return expirationDate.before(new Date());
    }

}
