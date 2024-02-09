package com.spring.models;

import java.util.Date;

public class Message {
    private String idemetteur;
    private String emetteur;
    private String contenu;
    private Date date;

    public Message(String emetteur, String contenu, Date date) {
        this.emetteur = emetteur;
        this.contenu = contenu;
        this.date = date;
    }

    public String getEmetteur() {
        return emetteur;
    }

    public void setEmetteur(String emetteur) {
        this.emetteur = emetteur;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIdemetteur() {
        return idemetteur;
    }

    public void setIdemetteur(String idemetteur) {
        this.idemetteur = idemetteur;
    }
}
