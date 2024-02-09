package com.spring.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.spring.models.view.AnnonceDetailView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "infoannonce")
public class InfoAnnonce {
    @Id
    private String id;

    private String annonce_id;
    private String auteur_id;
    private String auteur;
    private String lieu;
    private Long statut;
    private Long prix_vente;
    private Date date_annonce;
    private DetailVoiture detailvoiture;
    private String description;
    private List<Propriete> proprietes;
    private List<Photo> photos;

    public void updateTo(InfoAnnonce news) {
        this.setDescription(news.getDescription());
        this.setProprietes(news.getProprietes());
        this.setPrix_vente(news.getPrix_vente());
        this.setLieu(news.getLieu());
        this.detailvoiture = news.getDetailvoiture();
        // this.setPhotos(news.getPhotos());
    }

    public InfoAnnonce(String annonce_id, String description) {
        this.description = description;
        this.annonce_id = annonce_id;
        this.photos = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnnonce_id() {
        return annonce_id;
    }

    public void setAnnonce_id(String annonce_id) {
        this.annonce_id = annonce_id;
    }

    public String getAuteur_id() {
        return auteur_id;
    }

    public void setAuteur_id(String auteur_id) {
        this.auteur_id = auteur_id;
    }

    
    public String getDescription() {
        return description;
    }

     public Long getPrix_vente() {
        return prix_vente;
    }

    public void setPrix_vente(Long prix_vente) {
        if(prix_vente != null)
        this.prix_vente = prix_vente;
    }

    public void setDescription(String description) {
        if(description != null)
        this.description = description;
    }

    public List<Propriete> getProprietes() {
        return proprietes;
    }

    public void setProprietes(List<Propriete> proprietes) {
        if(proprietes != null)
        this.proprietes = proprietes;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        if(photos != null)
        this.photos = photos;
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

    public Long getStatut() {
        return statut;
    }

    public void setStatut(Long statut) {
        this.statut = statut;
    }

    public Date getDate_annonce() {
        return date_annonce;
    }

    public void setDate_annonce(Date date_annonce) {
        this.date_annonce = date_annonce;
    }

    public DetailVoiture getDetailvoiture() {
        return detailvoiture;
    }
    
    public void setDetailvoiture(DetailVoiture detailVoiture) {
        this.detailvoiture = detailVoiture;
    }

    public void setDetailvoitureANDInit(AnnonceDetailView detailAnnonce) {
        this.detailvoiture = new DetailVoiture(detailAnnonce);
        detailAnnonce.init(this);
        // System.out.println(" ** We init infoAnnonce value ** ");
    }
}
