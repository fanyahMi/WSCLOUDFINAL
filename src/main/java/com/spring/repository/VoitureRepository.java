package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.spring.models.Voiture;

public interface VoitureRepository extends JpaRepository<Voiture, Long> {
    @Query("SELECT MAX(v.id) FROM Voiture v")
    Long findMaxId();
}
