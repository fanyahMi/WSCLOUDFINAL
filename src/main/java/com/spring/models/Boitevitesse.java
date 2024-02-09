package com.spring.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Boitevitesse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_boitevitesse;

    private String boitevitesse;

    public Long getId_boitevitesse() {
        return id_boitevitesse;
    }

    public void setId_boitevitesse(Long id_boitevitesse) {
        this.id_boitevitesse = id_boitevitesse;
    }

    public String getBoitevitesse() {
        return boitevitesse;
    }

    public void setBoitevitesse(String boitevitesse) {
        this.boitevitesse = boitevitesse;
    }

}
