package com.spring.controller;

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

import com.spring.exception.TokenException;
import com.spring.models.Marque;
import com.spring.services.MarqueService;
import com.spring.services.TokenService;

import com.spring.utility.Response;

@RestController
@RequestMapping("/api/v1/marques")
@CrossOrigin
public class MarqueController {

    @Autowired
    private MarqueService marqueService;
    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ResponseEntity<Response> getAllMarques(@RequestHeader("Authorization") String authorizationHeader) {
        Response response = new Response();

        try {
            tokenService.checkSansRole(authorizationHeader);
            response.setStatus_code("200");
            response.setData(marqueService.getAllMarques());
            response.setMessage("réussi");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (TokenException e) {
            response.setStatus_code(e.getStatus_code());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, e.getStatus());
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getMarqueById(@PathVariable Integer id,
            @RequestHeader("Authorization") String authorizationHeader) {
        Response response = new Response();
        try {
            tokenService.checkSansRole(authorizationHeader);
            response.setStatus_code("200");
            response.setData(marqueService.getMarqueById(id));
            response.setMessage("réussi");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (TokenException e) {
            response.setStatus_code(e.getStatus_code());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, e.getStatus());
        }
    }

    @PostMapping
    public ResponseEntity<Response> addMarque(@RequestBody Marque marque,
            @RequestHeader("Authorization") String authorizationHeader) {
        Response response = new Response();
        try {
            marqueService.addMarque(marque);
            tokenService.checkRole(authorizationHeader, 10);
            response.setStatus_code("200");
            response.setMessage("réussi");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (TokenException e) {
            response.setStatus_code(e.getStatus_code());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, e.getStatus());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateMarque(@PathVariable Integer id, @RequestBody Marque newMarque,
            @RequestHeader("Authorization") String authorizationHeader) {
        Response response = new Response();
        try {
            tokenService.checkRole(authorizationHeader, 10);
            marqueService.updateMarque(id, newMarque);
            response.setStatus_code("200");
            response.setMessage("update réussi");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (TokenException e) {
            response.setStatus_code(e.getStatus_code());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, e.getStatus());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteMarque(@PathVariable Integer id,
            @RequestHeader("Authorization") String authorizationHeader) {
        Response response = new Response();
        try {
            tokenService.checkRole(authorizationHeader, 10);
            marqueService.deleteMarque(id);
            response.setStatus_code("200");
            response.setMessage("Supprimé réussi");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (TokenException e) {
            response.setStatus_code(e.getStatus_code());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, e.getStatus());
        }
    }

    /* Liste categorie par marque */
    @GetMapping("v1/categories/{idmarque}")
    public ResponseEntity<Response> listDeitailModelAnnee(
            @RequestHeader("Authorization") String authorizationHeader, @PathVariable Integer idmarque) {
        Response response = new Response();
        try {
            tokenService.checkSansRole(authorizationHeader);
            response.setStatus_code("200");
            Marque marque = new Marque();
            marque.setId_marque(idmarque);
            response.setData(marqueService.getListeCategorieMarque(marque));
            response.setMessage("réussi");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (TokenException e) {
            response.setStatus_code(e.getStatus_code());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, e.getStatus());
        } catch (Exception e) {
            response.setStatus_code("401");
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
    }

    /* Liste model par marque */
    @GetMapping("/v1/models/{idmarque}")
    public ResponseEntity<Response> listModelMarque(
            @RequestHeader("Authorization") String authorizationHeader, @PathVariable Integer idmarque) {
        Response response = new Response();
        try {
            tokenService.checkSansRole(authorizationHeader);
            response.setStatus_code("200");
            Marque marque = new Marque();
            marque.setId_marque(idmarque);
            response.setData(marqueService.getListeModelMarque(marque));
            response.setMessage("réussi");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (TokenException e) {
            response.setStatus_code(e.getStatus_code());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, e.getStatus());
        } catch (Exception e) {
            response.setStatus_code("401");
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
    }
}
