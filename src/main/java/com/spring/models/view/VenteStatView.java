package com.spring.models.view;

import java.sql.Date;

import com.spring.models.InfoAnnonce;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "v_statventemois")
public class VenteStatView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   
    private Long annee;
    private Long mois;
    private Long total_comission;
    
    public VenteStatView() {
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

    public Long getTotal_comission() {
        return total_comission;
    }

    public void setTotal_comission(Long total_comission) {
        this.total_comission = total_comission;
    }
}
