package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.models.Lieu;

public interface LieuRepository extends JpaRepository<Lieu, Long> {

}
