package com.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.token.TokenMobile;

public interface TokenMobileRepository extends JpaRepository<TokenMobile, Long> {
    Optional<TokenMobile> findByUtilisateurId(Long utilisateur_id);
    void deleteByUtilisateurId(Long utilisateurId);
}
