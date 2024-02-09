package com.spring.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.exception.TokenException;
import com.spring.services.view.EffectifStatViewService;
import com.spring.services.view.AnnonceStatViewService;
import com.spring.services.view.VenteStatViewService;
import com.spring.services.view.VenteViewService;
import com.spring.services.TokenService;

import com.spring.models.view.EffectifStatView;
import com.spring.models.Statistique;
import com.spring.models.view.AnnonceStatView;
import com.spring.models.view.VenteStatView;
import com.spring.models.view.VenteView;

import com.spring.utility.Response;

@RestController
@RequestMapping("api/v1/statistique")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StatistiqueController {

    @Autowired
    private EffectifStatViewService statEffectifService;
    @Autowired
    private AnnonceStatViewService statAnnonceService;
    @Autowired
    private VenteStatViewService statVenteService;
    @Autowired
    private VenteViewService venteService;
    @Autowired
    private TokenService tokenService;

    Long userId = (long) 1;

    @GetMapping
    public ResponseEntity<Response> getStatistique(@RequestHeader("Authorization") String authorizationHeader) {
        Response response = new Response();
        try {
            LocalDate aujourdhui = LocalDate.now();

            // Obtenir le mois et l'année actuels
            int moisActuel = aujourdhui.getMonthValue();
            int anneeActuelle = aujourdhui.getYear();

            Long annee = (long) anneeActuelle;
            Long mois = (long) moisActuel;

            EffectifStatView statEffectif = statEffectifService.getEffectifStat().get();
            List<AnnonceStatView> statAnnonce = statAnnonceService.getAllAnnonceStatOfMonth(mois, annee);
            List<VenteStatView> statVente = statVenteService.getAllVenteStatView();
            List<VenteView> venteOfMonth = venteService.getVenteViewOfMonth(mois, annee);

            Statistique stat = new Statistique(statEffectif, statAnnonce, statVente, venteOfMonth);

            tokenService.checkSansRole(authorizationHeader);
            response.setStatus_code("200");
            response.setData(stat);
            response.setMessage("réussi");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (TokenException e) {
            response.setStatus_code(e.getStatus_code());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, e.getStatus());
        }

    }

    @GetMapping("/free")
    public Statistique getStatistique() {
        Response response = new Response();
        try {
            Long annee = (long) 2024;
            Long mois = (long) 1;

            EffectifStatView statEffectif = statEffectifService.getEffectifStat().get();
            List<AnnonceStatView> statAnnonce = statAnnonceService.getAllAnnonceStatOfMonth(mois, annee);
            List<VenteStatView> statVente = statVenteService.getAllVenteStatView();
            List<VenteView> venteOfMonth = venteService.getVenteViewOfMonth(mois, annee);

            Statistique stat = new Statistique(statEffectif, statAnnonce, statVente, venteOfMonth);

            return stat;

        } catch (Exception e) {
            return null;
        }

    }

    @GetMapping("/vente")
    public ResponseEntity<Response> getVenteOfMonth() {
        Response response = new Response();
        Long annee = (long) 2024;
        Long mois = (long) 1;
        // try {
        List<VenteView> ventes = venteService.getVenteViewOfMonth(mois, annee);
        // tokenService.checkSansRole(authorizationHeader);
        response.setStatus_code("200");
        response.setData(ventes);
        response.setMessage("réussi");
        return new ResponseEntity<>(response, HttpStatus.OK);

        // } catch (TokenException e) {
        // response.setStatus_code(e.getStatus_code());
        // response.setMessage(e.getMessage());
        // return new ResponseEntity<>(response, e.getStatus());
        // }

    }
}
