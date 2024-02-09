package com.spring.repository.view;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.models.view.UtilisateurView;

public interface UtilisateurViewRepository extends JpaRepository<UtilisateurView, Long> {
    // Autre methode eto 
}
