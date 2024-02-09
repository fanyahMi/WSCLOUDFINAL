package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.models.Model;

public interface ModelRepository extends JpaRepository<Model, Integer> {

}
