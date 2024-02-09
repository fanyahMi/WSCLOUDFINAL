package com.spring.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_model;
    private Integer marque_id;
    private Integer categorie_id;
    private String model;

    public Integer getMarque_id() {
        return marque_id;
    }

    public void setMarque_id(Integer marque_id) {
        this.marque_id = marque_id;
    }

    public Integer getId_model() {
        return id_model;
    }

    public void setId_model(Integer id_model) {
        this.id_model = id_model;
    }

    public Integer getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(Integer categorie_id) {
        this.categorie_id = categorie_id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    /****
     * Controlle de valeur
     * 
     * @throws Exception
     */
    public void setId_model(String id_model) throws Exception {
        try {
            setId_model(Integer.parseInt(id_model));
        } catch (Exception e) {
            throw new Exception("Il y à une erreur ");
        }

    }

}
