package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.models.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Integer> {

}
