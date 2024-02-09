package com.spring.models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Comission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comission")
    private Long idComission;

    private Long taux;

    private Date datecomission;

    public void test() {

    };

    public Long getIdComission() {
        return idComission;
    }

    public void setIdComission(Long idComission) {
        this.idComission = idComission;
    }

    public Long getTaux() {
        return taux;
    }

    public void setTaux(Long taux) {
        this.taux = taux;
    }

    public Date getDatecomission() {
        return datecomission;
    }

    public void setDatecomission(Date datecomission) {
        this.datecomission = datecomission;
    }

}
