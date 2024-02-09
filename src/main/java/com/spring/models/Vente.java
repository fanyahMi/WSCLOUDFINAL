package com.spring.models;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_vente;
    private Long annonce_id; 
    private Long acheteur_id; 
    private Long prix_achat; 
    private Long taux_comission; 
    private Date date_achat;

    public Vente() {
    }

    public Long getId_vente() {
        return id_vente;
    }

    public void setId_vente(Long id_vente) {
        this.id_vente = id_vente;
    }

    public Long getAnnonce_id() {
        return annonce_id;
    }

    public void setAnnonce_id(Long annonce_id) {
        this.annonce_id = annonce_id;
    }

    public Long getAcheteur_id() {
        return acheteur_id;
    }

    public void setAcheteur_id(Long acheteur_id) {
        this.acheteur_id = acheteur_id;
    }

    public Long getPrix_achat() {
        return prix_achat;
    }

    public void setPrix_achat(Long prix_achat) {
        this.prix_achat = prix_achat;
    }

    public Long getTaux_comission() {
        return taux_comission;
    }

    public void setTaux_comission(Long taux_comission) {
        this.taux_comission = taux_comission;
    }

    public Date getDate_achat() {
        return date_achat;
    }

    public void setDate_achat(Date date_achat) {
        this.date_achat = date_achat;
    }
}
