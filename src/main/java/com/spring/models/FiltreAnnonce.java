package com.spring.models;

import java.sql.Date;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class FiltreAnnonce {
    private String keyWord;
    private String lieu;
    private Long min_prix = 0L;
    private Long max_prix = Long.MAX_VALUE;
    private Date deb_date;
    private Date fin_date;
    private Long min_km = 0L;
    private Long max_km = Long.MAX_VALUE;
    private String marque;
    private String categorie;
    private Long min_annee = 0L;
    private Long max_annee = 3000L;
    private String carburant;

    public void initNumericNullFilter() {
        if (this.min_prix == null) {
            this.min_prix = 0L;
        }
        if (this.max_prix == null) {
            this.max_prix = Long.MAX_VALUE;
        }
        if (this.min_km == null) {
            this.min_km = 0L;
        }
        if (this.max_km == null) {
            this.max_km = Long.MAX_VALUE;
        }
        if (this.min_annee == null) {
            this.min_annee = 0L;
        }
        if (this.max_annee == null) {
            this.max_annee = Long.MAX_VALUE;
        }
    }

    public void setQueryCriteria(Query query) {
        // Initialiser les filtres numerique null
        this.initNumericNullFilter();

        // Avoir uniquement les annonces valide
        query.addCriteria(Criteria.where("statut").is(2L));

        // Condition sur lieu
        if (this.getLieu() != null) {
            query.addCriteria(Criteria.where("lieu").is(this.getLieu()));
        }

        // Condition sur prix_vente
        query.addCriteria(Criteria.where("prix_vente").gte(this.getMin_prix()).lte(this.getMax_prix()));

        // Condition sur detailvoiture.kilometrage
        query.addCriteria(Criteria.where("detailvoiture.kilometrage").gte(this.getMin_km()).lte(this.getMax_km()));

        // Condition sur detailvoiture.marque
        if (this.getMarque() != null) {
            query.addCriteria(Criteria.where("detailvoiture.marque").is(this.getMarque()));
        }

        // Condition sur detailvoiture.categorie
        if (this.getCategorie() != null) {
            query.addCriteria(Criteria.where("detailvoiture.categorie").is(this.getCategorie()));
        }

        // Condition sur detailvoiture.carburant
        if (this.getCarburant() != null) {
            query.addCriteria(Criteria.where("detailvoiture.carburant").is(this.getCarburant()));
        }

        // Condition sur detailvoiture.annee
        query.addCriteria(Criteria.where("detailvoiture.annee").gte(this.getMin_annee()).lte(this.getMax_annee()));

        // Condition sur description, detailvoiture.model, detailvoiture.matricule et
        // proprietes.description
        if (this.getKeyWord() != null) {
            query.addCriteria(new Criteria().orOperator(
                    Criteria.where("description").regex(this.getKeyWord(), "i"),
                    Criteria.where("detailvoiture.model").regex(this.getKeyWord(), "i"),
                    Criteria.where("detailvoiture.matricule").regex(this.getKeyWord(), "i"),
                    Criteria.where("proprietes.titre").regex(this.getKeyWord(), "i"),
                    Criteria.where("proprietes.description").regex(this.getKeyWord(), "i")));
        }
    }

    public FiltreAnnonce() {
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Long getMin_prix() {
        return min_prix;
    }

    public void setMin_prix(Long min_prix) {
        this.min_prix = min_prix;
    }

    public Long getMax_prix() {
        return max_prix;
    }

    public void setMax_prix(Long max_prix) {
        this.max_prix = max_prix;
    }

    public Date getDeb_date() {
        return deb_date;
    }

    public void setDeb_date(Date deb_date) {
        this.deb_date = deb_date;
    }

    public Date getFin_date() {
        return fin_date;
    }

    public void setFin_date(Date fin_date) {
        this.fin_date = fin_date;
    }

    public Long getMin_km() {
        return min_km;
    }

    public void setMin_km(Long min_km) {
        this.min_km = min_km;
    }

    public Long getMax_km() {
        return max_km;
    }

    public void setMax_km(Long max_km) {
        this.max_km = max_km;
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

    public Long getMin_annee() {
        return min_annee;
    }

    public void setMin_annee(Long min_annee) {
        this.min_annee = min_annee;
    }

    public Long getMax_annee() {
        return max_annee;
    }

    public void setMax_annee(Long max_annee) {
        this.max_annee = max_annee;
    }

    public String getCarburant() {
        return carburant;
    }

    public void setCarburant(String carburant) {
        this.carburant = carburant;
    }

}
