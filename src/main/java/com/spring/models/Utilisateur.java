package com.spring.models;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_utilisateur;

    private String nom;

    private String prenom;

    private int genre;

    private Date date_naissance;

    private String email;

    private String mdp;

    private int roles;

    public Utilisateur() {
    }

    public Utilisateur(String email, String mdp) {
        this.email = email;
        this.mdp = mdp;
    }

    public Utilisateur(String nom, String prenom, int genre, Date date_naissance, String email, String mdp,
            String mdp2) throws Exception {
        setNom(nom);
        setPrenom(prenom);
        setGenre(genre);
        setDate_naissance(date_naissance);
        setEmail(email);
        setMdp(mdp, mdp2);
    }

    public Long getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(Long id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws Exception {
        if (nom.equals("") || nom == null)
            throw new Exception("Impossible de  voir le nom");
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception {
        if (email.equals("") || email == null)
            throw new Exception("Impossible de voir l'email");
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) throws Exception {
        if (mdp.equals("") || mdp == null)
            throw new Exception("Impossible de voir le le mot de passe");
        this.mdp = mdp;
    }

    public void setMdp(String mdp1, String mdp2) throws Exception {
        if (!mdp1.equals("") && !mdp2.equals("")) {
            if (!mdp1.equals(mdp2))
                throw new Exception("Les 2 mots de passe doit etre egale");
            setMdp(mdp1);
        }

    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) throws Exception {
        if (prenom.equals("") || prenom == null)
            throw new Exception("Impossible de voir le prenom  ");
        this.prenom = prenom;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) throws Exception {
        if (date_naissance == null) {
            throw new Exception("Ajoute la date de naissance");
        }
        this.date_naissance = date_naissance;
    }

    public int getRoles() {
        return roles;
    }

    public void setRoles(int roles) {
        this.roles = roles;
    }

}
