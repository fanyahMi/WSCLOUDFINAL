package com.spring.models.view;

import java.sql.Date;

import com.spring.models.InfoAnnonce;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "v_statannoncemois")
public class AnnonceStatView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   
    private Long annee;
    private Long mois;
    private Long statut;
    private Long nombre_annonces;  

    public AnnonceStatView() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getStatut() {
        return statut;
    }

    public void setStatut(Long statut) {
        this.statut = statut;
    }

    public Long getNombre_annonces() {
        return nombre_annonces;
    }

    public void setNombre_annonces(Long nombre_annonces) {
        this.nombre_annonces = nombre_annonces;
    }
}
