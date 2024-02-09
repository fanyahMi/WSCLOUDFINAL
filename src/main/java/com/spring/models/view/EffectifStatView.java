package com.spring.models.view;

import java.sql.Date;

import com.spring.models.InfoAnnonce;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "v_effectifstat")
public class EffectifStatView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;     
    private Long total_utilisateur;
    private Long total_commission; 
    private Long total_annonce;
    private Long total_vente;

    public EffectifStatView() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotal_utilisateur() {
        return total_utilisateur;
    }

    public void setTotal_utilisateur(Long total_utilisateur) {
        this.total_utilisateur = total_utilisateur;
    }

    public Long getTotal_commission() {
        return total_commission;
    }

    public void setTotal_commission(Long total_commission) {
        this.total_commission = total_commission;
    }

    public Long getTotal_annonce() {
        return total_annonce;
    }

    public void setTotal_annonce(Long total_annonce) {
        this.total_annonce = total_annonce;
    }

    public Long getTotal_vente() {
        return total_vente;
    }

    public void setTotal_vente(Long total_vente) {
        this.total_vente = total_vente;
    }
}
