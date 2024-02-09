package com.spring.services;

import com.spring.exception.TokenException;
import com.spring.repository.TokenRepository;
import com.spring.token.JwtUtil2;
import com.spring.token.Token;

import io.jsonwebtoken.Claims;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    @Autowired
    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    // Méthode pour enregistrer un nouveau token
    public Token saveToken(Token token) {
        return tokenRepository.save(token);
    }

    @Transactional
    public void deleteTokenByToken(String token) {
        tokenRepository.deleteByToken(token);
    }

    public void logout(String token) throws TokenException {
        tokenRepository.deleteByToken(checkAuthorizationBearer(token));
    }

    public Optional<Token> findTokenByConditions(String token, Date currentDate) {
        return tokenRepository.findByTokenAndDateExpirationBefore(token, currentDate);
    }

    public Token findByToken(String token) {
        Optional<Token> optionalToken = tokenRepository.findByToken(token);
        return optionalToken.orElse(null);
    }

    public Claims getClaims(Token token) throws TokenException {
        try {
            JwtUtil2 jwttoken = new JwtUtil2();
            return jwttoken.extractClaims(token);
        } catch (Exception e) {
            deleteTokenByToken(token.getToken());
            throw new TokenException("Session expirée. Veuillez vous reconnecter.", "401", HttpStatus.UNAUTHORIZED);
        }
    }

    public String checkAuthorizationBearer(String authorizationHeader) throws TokenException {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new TokenException("Accès refusé. Vous n'avez pas l'autorisation d'accéder à cette ressource.", "403",
                    HttpStatus.FORBIDDEN);
        }
        return authorizationHeader.substring(7);
    }

    public Claims validationToken(String authorizationHeader) throws TokenException {
        String token = checkAuthorizationBearer(authorizationHeader);
        Token tk = findByToken(token);
        if (tk == null) {
            throw new TokenException("Veuillez vous connecter. ", "401", HttpStatus.UNAUTHORIZED);
        }
        tk.setToken(token);
        return getClaims(tk);
    }

    public void checkRole(String authorizationHeader, int role) throws TokenException {
        Claims claims = validationToken(authorizationHeader);
        if (!claims.get("role").equals(role)) {
            throw new TokenException("Accès interdit. Vous n'avez pas le droit d'accéder à cette ressource", "403",
                    HttpStatus.FORBIDDEN);
        }
    }

    public Claims getClaims(String authorizationHeader) throws TokenException {
        return validationToken(authorizationHeader);
    }

    public void checkSansRole(String authorizationHeader) throws TokenException {
        validationToken(authorizationHeader);
    }

}