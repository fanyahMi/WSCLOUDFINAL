/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.dao.connexion;

/**
 *
 * @author Hasinjo
 */
public class Connexion_projet extends Connexion {

    public Connexion_projet(String base, String user, String password, String database) {
        super(base, user, password, database);
    }

    public Connexion_projet() {
        setBase("postgresql");
        setUser("avnadmin");
        setPassword("AVNS_PhrlTOrIXIqdy1wlFM5");
        setDatabase("defaultdb");
        setHote("pg-3c68f313-projethasinjo-a38f.a.aivencloud.com");
        setPort("28071");
    }

}
