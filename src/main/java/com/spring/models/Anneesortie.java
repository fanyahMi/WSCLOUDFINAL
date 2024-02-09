package com.spring.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Anneesortie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_anneesortie;
    private Long modelId;
    private int annee;

    public Long getId_anneesortie() {
        return id_anneesortie;
    }

    public void setId_anneesortie(Long id_anneesortie) {
        this.id_anneesortie = id_anneesortie;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

}
