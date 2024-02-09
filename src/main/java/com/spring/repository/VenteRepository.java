package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.models.Vente;

public interface VenteRepository extends JpaRepository<Vente, Long> {

}
