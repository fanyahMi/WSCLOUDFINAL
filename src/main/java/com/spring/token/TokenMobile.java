package com.spring.token;

import jakarta.persistence.*;

@Entity
@Table(name = "tokenmobile")
public class TokenMobile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tokenmobile;
    private String tokenmobile;
    private Long utilisateurId;

    public TokenMobile() {
    }

    public TokenMobile(Long utilisateurId, String tokenmobile) {
        this.utilisateurId = utilisateurId;
        this.tokenmobile = tokenmobile;
    }

    public Long getId_tokenmobile() {
        return id_tokenmobile;
    }

    public void setId_tokenmobile(Long id_tokenmobile) {
        this.id_tokenmobile = id_tokenmobile;
    }

    public String getTokenmobile() {
        return tokenmobile;
    }

    public void setTokenmobile(String tokenmobile) {
        this.tokenmobile = tokenmobile;
    }

    public Long getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }
}
