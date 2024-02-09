package com.spring.services;

import com.spring.exception.TokenException;
import com.spring.repository.TokenMobileRepository;
import com.spring.token.JwtUtil2;
import com.spring.token.TokenMobile;

import io.jsonwebtoken.Claims;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TokenMobileService {

    @Autowired
    private TokenMobileRepository tokenMobileRepository;

    public TokenMobile save(Long utilisateur_id, String tokenmobile) {
        if(tokenmobile == null || tokenmobile.isEmpty()) { return null; }

        Optional<TokenMobile> tokenMobileOptional = tokenMobileRepository.findByUtilisateurId(utilisateur_id);

        if(tokenMobileOptional.isPresent()) {
            TokenMobile tokenMobile = tokenMobileOptional.get();
            tokenMobile.setTokenmobile(tokenmobile);
            return tokenMobileRepository.save(tokenMobile);
        }

        TokenMobile tokenMobile = new TokenMobile(utilisateur_id, tokenmobile);
        return tokenMobileRepository.save(tokenMobile);
    }

    public TokenMobile findByUtilisateurId(Long utilisateur_id) {
        Optional<TokenMobile> tokenOptional = tokenMobileRepository.findByUtilisateurId(utilisateur_id);
        return tokenOptional.orElse(null);
    }

    @Transactional
    public void delete(Long utilisateur_id) {
        tokenMobileRepository.deleteByUtilisateurId(utilisateur_id);
    }
}
