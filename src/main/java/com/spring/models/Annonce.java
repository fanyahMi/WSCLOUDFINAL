package com.spring.models;

import java.sql.Date;

import org.springframework.http.HttpStatus;

import com.spring.exception.TokenException;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class Annonce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAnnonce;
    private Long voitureId;
    private Long lieuId;
    private Long vendeurId;
    private double prixVente;
    private int statut;
    private Date dateAnnonce;
    private Date dateConfirmation;

    @Transient
    Voiture voiture;
    @Transient
    InfoAnnonce infoAnnonce;

    public void updateTo(Annonce news) {
        this.setLieuId(news.getLieuId());
        this.setVendeurId(news.getVendeurId());
        this.setPrixVente(news.getPrixVente());
        this.voiture = news.getVoiture();
        this.infoAnnonce = news.getInfoAnnonce();
        this.infoAnnonce.setAnnonce_id(this.idAnnonce.toString());
        this.voiture.setIdVoiture(this.voitureId);
    }

    public InfoAnnonce getInfoAnnonce() {
        return infoAnnonce;
    }

    public void setInfoAnnonce(InfoAnnonce infoAnnonce) {
        this.infoAnnonce = infoAnnonce;
    }

    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }

    public Long getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(Long idAnnonce) {
        if (idAnnonce != null)
            this.idAnnonce = idAnnonce;
    }

    public Long getVoitureId() {
        return voitureId;
    }

    public void setVoitureId(Long voitureId) {
        this.voitureId = voitureId;
    }

    public Long getLieuId() {
        return lieuId;
    }

    public void setLieuId(Long lieuId) {
        if (lieuId != null)
            this.lieuId = lieuId;
    }

    public Long getVendeurId() {
        return vendeurId;
    }

    public void setVendeurId(Long vendeurId) {
        if (vendeurId != null)
            this.vendeurId = vendeurId;
    }

    public double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(double prixVente) {
        if (prixVente != 0)
            this.prixVente = prixVente;
    }

    public Date getDateAnnonce() {
        return dateAnnonce;
    }

    public void setDateAnnonce(Date dateAnnonce) {
        this.dateAnnonce = dateAnnonce;
    }

    public Date getDateConfirmation() {
        return dateConfirmation;
    }

    public void setDateConfirmation(Date dateConfirmation) {
        this.dateConfirmation = dateConfirmation;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public void checkAuthorization(Long userId) throws TokenException {
        if (this.vendeurId != userId) {
            throw new TokenException("Accès refusé. Vous n'avez pas l'autorisation pour modifier cette ressource.",
                    "403",
                    HttpStatus.FORBIDDEN);
        }
    }
}
