package com.spring.models.view;

import java.sql.Date;

import com.spring.models.InfoAnnonce;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "v_vente")
public class VenteView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_vente;
    private Long annonce_id;
    private Long acheteur_id;
    private Long prix_achat;
    private Long taux_comission;
    private Date date_achat;
    private String vendeur;
    private String acheteur;
    private Long annee;
    private Long mois;
    private Long total_comission;
    
    public VenteView() {
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

    public String getVendeur() {
        return vendeur;
    }

    public void setVendeur(String vendeur) {
        this.vendeur = vendeur;
    }

    public String getAcheteur() {
        return acheteur;
    }

    public void setAcheteur(String acheteur) {
        this.acheteur = acheteur;
    }

    public Long getAnnee() {
        return annee;
    }

    public void setAnnee(Long annee) {
        this.annee = annee;
    }

    public Long getMois() {
        return mois;
    }

    public void setMois(Long mois) {
        this.mois = mois;
    }

    public Long getTotal_comission() {
        return total_comission;
    }

    public void setTotal_comission(Long total_comission) {
        this.total_comission = total_comission;
    }

}
