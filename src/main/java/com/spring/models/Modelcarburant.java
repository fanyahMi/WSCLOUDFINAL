package com.spring.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Modelcarburant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idModelcarburant;
    private Long modelId;
    private Long carburantId;

    public Long getIdModelcarburant() {
        return idModelcarburant;
    }

    public void setIdModelcarburant(Long idModelcarburant) {
        this.idModelcarburant = idModelcarburant;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public Long getCarburantId() {
        return carburantId;
    }

    public void setCarburantId(Long carburantId) {
        this.carburantId = carburantId;
    }

}
