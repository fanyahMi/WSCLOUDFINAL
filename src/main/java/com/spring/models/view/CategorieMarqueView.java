package com.spring.models.view;

import com.spring.dao.annotation.Column;
import com.spring.dao.annotation.Table;
import com.spring.dao.dao.Generic2;

@Table(libelle = "v_categorie_marque")
public class CategorieMarqueView extends Generic2 {
    @Column(libelle = "id_marque")
    private Integer id_marque;

    @Column(libelle = "marque")
    private String marque;

    @Column(libelle = "id_categorie")
    private Integer idCategorie;

    @Column(libelle = "categorie")
    private String categorie;

    public Integer getId_marque() {
        return id_marque;
    }

    public void setId_marque(Integer id_marque) {
        this.id_marque = id_marque;
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

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

}
