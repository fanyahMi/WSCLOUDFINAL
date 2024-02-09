package com.spring.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Voiture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVoiture;
    private Long modelId;
    private String matricule;
    private Long utilisateurId;
    private double kilometrage;
    private Long modelcarburantId;
    private Long anneesortieId;

    public void updateTo(Voiture news) {
        this.setModelId(news.getModelId());
        this.setMatricule(news.getMatricule());
        this.setKilometrage(news.getKilometrage());
        this.setModelcarburantId(news.getModelcarburantId());
        this.setAnneesortieId(news.getAnneesortieId());
    }

    public Long getIdVoiture() {
        return idVoiture;
    }

    public void setIdVoiture(Long idVoiture) {
        this.idVoiture = idVoiture;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        if(modelId != null)
        this.modelId = modelId;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        if(matricule != null)
        this.matricule = matricule;
    }

    public Long getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Long utilisateurId) {
        if(utilisateurId != null)
        this.utilisateurId = utilisateurId;
    }

    public double getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(double kilometrage) {
        if(kilometrage != 0)
        this.kilometrage = kilometrage;
    }

    public Long getModelcarburantId() {
        return modelcarburantId;
    }

    public void setModelcarburantId(Long modelcarburantId) {
        if(modelcarburantId != null)
        this.modelcarburantId = modelcarburantId;
    }

    public Long getAnneesortieId() {
        return anneesortieId;
    }

    public void setAnneesortieId(Long anneesortieId) {
        if(anneesortieId != null)
        this.anneesortieId = anneesortieId;
    }

}
