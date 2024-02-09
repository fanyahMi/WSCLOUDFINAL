package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.models.Anneesortie;

@Repository
public interface AnneesortieRepository extends JpaRepository<Anneesortie, Long> {
    List<Anneesortie> findByModelId(Long model_id);
}
