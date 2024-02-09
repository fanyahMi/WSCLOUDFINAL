package com.spring.models.view;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.spring.dao.annotation.Column;
import com.spring.dao.annotation.Table;
import com.spring.dao.dao.Generic2;

@Table(libelle = "v_model_details")
public class ModelDetailView extends Generic2 {
    @Column(libelle = "id_model")
    private Integer idModel;
    @Column(libelle = "model")
    private String model;
    @Column(libelle = "id_marque")
    private Integer idMarque;
    @Column(libelle = "marque")
    private String marque;
    @Column(libelle = "id_categorie")
    private Integer idCategorie;
    @Column(libelle = "categorie")
    private String categorie;

    public Integer getIdModel() {
        return idModel;
    }

    public void setIdModel(Integer idModel) {
        this.idModel = idModel;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getIdMarque() {
        return idMarque;
    }

    public void setIdMarque(Integer idMarque) {
        this.idMarque = idMarque;
    }

    public void setIdMarque(String idMarque) throws Exception {
        try {
            setIdMarque(Integer.parseInt(idMarque));
        } catch (Exception e) {
            throw new Exception("Impossible de voire cette marque");
        }
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public Integer getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Integer idCategorie) {
        this.idCategorie = idCategorie;
    }

    public void setIdCategorie(String idCategorie) throws Exception {
        try {
            setIdCategorie(Integer.parseInt(idCategorie));
        } catch (Exception e) {
            throw new Exception("Impossible de voir ce categorie");
        }
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public List<ModelDetailView> getListeModel(Connection connexion) throws SQLException, Exception {
        String query = "select * from v_model_details ";
        boolean check = false;
        if (this.idMarque != null) {
            check = true;
            query += " where  id_marque = " + this.idMarque;
        }
        if (this.idCategorie != null) {
            if (check)
                query += " and ";
            else {
                query += " where ";
            }
            query += " id_categorie = " + this.idCategorie;
        }
        return new ModelDetailView().select(connexion, query);
    }

}
