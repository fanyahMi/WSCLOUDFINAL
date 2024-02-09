package com.spring.controller;

import com.spring.exception.TokenException;
import com.spring.models.Utilisateur;
import com.spring.services.TokenMobileService;
import com.spring.services.TokenService;
import com.spring.services.UtilisateurService;
import com.spring.services.view.UtilisateurViewService;
import com.spring.token.JwtUtil2;
import com.spring.token.Token;
import com.spring.utility.Response;

import io.jsonwebtoken.Claims;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private UtilisateurViewService utilisateurViewService;
    @Autowired
    private TokenMobileService tokenMobileService;
    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ResponseEntity<Response> getAllInfoAnnonces(@RequestHeader("Authorization") String authorizationHeader) {
        Response response = new Response();
        try {
            tokenService.checkSansRole(authorizationHeader);
            response.setStatus_code("200");
            response.setData(utilisateurViewService.getAllUtilisateurView());
            response.setMessage("réussi");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (TokenException e) {
            response.setStatus_code(e.getStatus_code());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, e.getStatus());
        }
    }

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping("/v1/logout")
    public ResponseEntity<Response> logout(@RequestHeader("Authorization") String authorizationHeader) {
        Response response = new Response();
        try {
            tokenService.logout(authorizationHeader);
            response.setStatus(HttpStatus.OK);
            response.setStatus_code("200");
            response.setMessage("");
        } catch (Exception e) {
            response.setMessage("Email ou mot de passe incorrect ");
            response.setStatus_code("401");
            response.setStatus(HttpStatus.UNAUTHORIZED);
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/v2/logout")
    public ResponseEntity<Response> logoutMobile(@RequestHeader("Authorization") String authorizationHeader) {
        Response response = new Response();
        try {
            tokenService.checkSansRole(authorizationHeader);
            Claims claims = tokenService.getClaims(authorizationHeader);
            Long userId = Long.parseLong(claims.get("idUtilisateur").toString());

            tokenService.logout(authorizationHeader);
            tokenMobileService.delete(userId); // Supprimer le tokenMobile de l'utilisateur
            response.setStatus(HttpStatus.OK);
            response.setStatus_code("200");
            response.setMessage("");
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage("Un erreur c'est produite");
            response.setStatus_code("401");
            response.setStatus(HttpStatus.UNAUTHORIZED);
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/v1/login")
    public ResponseEntity<Response> login(@RequestParam("email") String email, @RequestParam("mdp") String mdp)
            throws Exception {
        Response response = new Response();
        Utilisateur utilisateur = utilisateurService.findByEmailAndPassword(email, mdp);

        if (utilisateur != null) {
            JwtUtil2 j = new JwtUtil2();
            Map<String, Object> res = j.generateToken(utilisateur);
            Token token = new Token();
            token.setCle((String) res.get("cle"));
            token.setToken((String) res.get("token"));
            token.setDateCreation(new Date(((java.util.Date) res.get("date")).getTime()));
            token.setDateExpiration(new Date(((java.util.Date) res.get("expirer")).getTime()));
            tokenService.saveToken(token);
            response.setData(token.getToken());
            response.setStatus(HttpStatus.OK);
            response.setStatus_code("200");
            response.setMessage("");
        } else {
            response.setMessage("Email ou mot de passe incorrect ");
            response.setStatus_code("401");
            response.setStatus(HttpStatus.UNAUTHORIZED);
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/v2/login")
    public ResponseEntity<Response> loginMobile(@RequestParam("email") String email,
            @RequestParam("mdp") String mdp,
            @RequestHeader("MobileToken") String tokenMobile) throws Exception {
        Response response = new Response();
        Utilisateur utilisateur = utilisateurService.findByEmailAndPassword(email, mdp);

        if (utilisateur != null) {
            JwtUtil2 j = new JwtUtil2();
            Map<String, Object> res = j.generateToken(utilisateur);
            Token token = new Token();
            token.setCle((String) res.get("cle"));
            token.setToken((String) res.get("token"));
            token.setDateCreation(new Date(((java.util.Date) res.get("date")).getTime()));
            token.setDateExpiration(new Date(((java.util.Date) res.get("expirer")).getTime()));
            tokenService.saveToken(token);
            tokenMobileService.save(utilisateur.getId_utilisateur(), tokenMobile); // Ajouter ou modifier le tokenMobile
                                                                                   // de l'utilisateur
            response.setData(token.getToken());
            response.setStatus(HttpStatus.OK);
            response.setStatus_code("200");
            response.setMessage("");
        } else {
            response.setMessage("Email ou mot de passe incorrect ");
            response.setStatus_code("401");
            response.setStatus(HttpStatus.UNAUTHORIZED);
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/v1/inscription")
    public ResponseEntity<Response> inscription(@RequestParam("email") String email,
            @RequestParam("password") String mdp, @RequestParam("password2") String mdp2,
            @RequestParam("nom") String nom,
            @RequestParam("prenom") String prenom, @RequestParam("genre") int genre,
            @RequestParam("date_naissance") Date dateNaissance) {
        Response response = new Response();
        try {
            Utilisateur utilisateur = new Utilisateur(nom, prenom, genre, dateNaissance, email, mdp, mdp2);
            utilisateur.setRoles(1);
            utilisateur = utilisateurService.inscrireUtilisateur(utilisateur);
            JwtUtil2 j = new JwtUtil2();
            Map<String, Object> res = j.generateToken(utilisateur);
            Token token = new Token();
            token.setCle((String) res.get("cle"));
            token.setToken((String) res.get("token"));
            token.setDateCreation(new Date(((java.util.Date) res.get("date")).getTime()));
            token.setDateExpiration(new Date(((java.util.Date) res.get("expirer")).getTime()));
            tokenService.saveToken(token);
            response.setData(token.getToken());
            response.setStatus(HttpStatus.OK);
            response.setStatus_code("200");
            response.setMessage("");
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setStatus_code("401");
            response.setStatus(HttpStatus.UNAUTHORIZED);
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/v1/login/admin")
    public ResponseEntity<Response> loginadmin(@RequestParam("email") String email, @RequestParam("mdp") String mdp)
            throws Exception {
        Response response = new Response();
        Utilisateur utilisateur = utilisateurService.findByEmailAndPassword(email, mdp);
        if (utilisateur != null) {
            if (utilisateur.getRoles() != 10) {
                response.setMessage("Email ou mot de passe incorrect ");
                response.setStatus_code("401");
                response.setStatus(HttpStatus.UNAUTHORIZED);
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }
            JwtUtil2 j = new JwtUtil2();
            Map<String, Object> res = j.generateToken(utilisateur);
            Token token = new Token();
            token.setCle((String) res.get("cle"));
            token.setToken((String) res.get("token"));
            token.setDateCreation(new Date(((java.util.Date) res.get("date")).getTime()));
            token.setDateExpiration(new Date(((java.util.Date) res.get("expirer")).getTime()));
            tokenService.saveToken(token);
            response.setData(token.getToken());
            response.setStatus(HttpStatus.OK);
            response.setStatus_code("200");
            response.setMessage("");
        } else {
            response.setMessage("Email ou mot de passe incorrect ");
            response.setStatus_code("401");
            response.setStatus(HttpStatus.UNAUTHORIZED);
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
