package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.models.Carburant;

@Repository
public interface CarburantRepository extends JpaRepository<Carburant, Long> {

}
