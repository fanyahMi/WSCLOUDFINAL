package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.models.Comission;

@Repository
public interface ComissionRepository extends JpaRepository<Comission, Long> {
    @Query("SELECT c.taux FROM Comission c ORDER BY c.idComission DESC limit 1")
    Long findLatestComission();

    @Query("SELECT c FROM Comission c ORDER BY c.idComission DESC")
    List<Comission> listCommissions();
}
