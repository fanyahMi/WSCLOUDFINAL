package com.spring.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Marque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_marque;
    private String marque;

    public Integer getId_marque() {
        return id_marque;
    }

    public void setId_marque(Integer id_marque) {
        this.id_marque = id_marque;
    }

    public void setId_marque(String id_marque) throws Exception {
        try {
            setId_marque(Integer.parseInt(id_marque));
        } catch (Exception e) {
            throw new Exception("Il y a une erreur ");
        }
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }
}
