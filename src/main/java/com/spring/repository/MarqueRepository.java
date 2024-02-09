package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.models.Marque;

public interface MarqueRepository extends JpaRepository<Marque, Integer> {

}