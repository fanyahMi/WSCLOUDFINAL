package com.spring.models;

import com.spring.models.view.EffectifStatView;

import java.util.List;

import com.spring.models.view.AnnonceStatView;
import com.spring.models.view.VenteStatView;
import com.spring.models.view.VenteView;

public class Statistique {
    private EffectifStatView statEffectif; 
    private List<AnnonceStatView> statAnnonce;
    private List<VenteStatView> statVente;
    private List<VenteView> venteOfMonth;
    
    public Statistique(EffectifStatView statEffectif, List<AnnonceStatView> statAnnonce,
            List<VenteStatView> statVente, List<VenteView> ventes) {
        this.statEffectif = statEffectif;
        this.statAnnonce = statAnnonce;
        this.statVente = statVente;
        this.venteOfMonth = ventes;
    }

    public Statistique() {
    }

    public EffectifStatView getStatEffectif() {
        return statEffectif;
    }

    public void setStatEffectif(EffectifStatView statEffectif) {
        this.statEffectif = statEffectif;
    }

    public List<AnnonceStatView> getStatAnnonce() {
        return statAnnonce;
    }

    public void setStatAnnonce(List<AnnonceStatView> statAnnonce) {
        this.statAnnonce = statAnnonce;
    }

    public List<VenteStatView> getStatVente() {
        return statVente;
    }

    public void setStatVente(List<VenteStatView> statVente) {
        this.statVente = statVente;
    }

    public List<VenteView> getVenteOfMonth() {
        return venteOfMonth;
    }

    public void setVenteOfMonth(List<VenteView> venteOfMonth) {
        this.venteOfMonth = venteOfMonth;
    }

}

    
    
