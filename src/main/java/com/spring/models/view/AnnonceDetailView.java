package com.spring.models.view;

import java.sql.Date;

import com.spring.models.InfoAnnonce;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "v_detail_annonce")
public class AnnonceDetailView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_annonce;
    private String auteur;
    private String lieu;
    private Long prix_vente;
    private Date date_annonce;
    private String model;
    private String matricule;
    private Long kilometrage;
    private String marque;
    private String categorie;
    private Long annee;
    private String carburant;

    public AnnonceDetailView() {
    }

    public void init(InfoAnnonce infoAnnonce) {
        infoAnnonce.setAuteur(auteur);
        infoAnnonce.setLieu(lieu);
        infoAnnonce.setPrix_vente(prix_vente);
    }

    public Long getId_annonce() {
        return id_annonce;
    }

    public void setId_annonce(Long id_annonce) {
        this.id_annonce = id_annonce;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Long getPrix_vente() {
        return prix_vente;
    }

    public void setPrix_vente(Long prix_vente) {
        this.prix_vente = prix_vente;
    }

    public Date getDate_annonce() {
        return date_annonce;
    }

    public void setDate_annonce(Date date_annonce) {
        this.date_annonce = date_annonce;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public Long getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(Long kilometrage) {
        this.kilometrage = kilometrage;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Long getAnnee() {
        return annee;
    }

    public void setAnnee(Long annee) {
        this.annee = annee;
    }

    public String getCarburant() {
        return carburant;
    }

    public void setCarburant(String carburant) {
        this.carburant = carburant;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
