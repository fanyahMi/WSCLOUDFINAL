package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.exception.TokenException;
import com.spring.models.FiltreAnnonce;
import com.spring.services.LieuService;
import com.spring.services.TokenService;
import com.spring.utility.Response;

@RestController
@RequestMapping("/api/lieux")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LieuController {
    @Autowired
    private LieuService lieuService;
    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ResponseEntity<Response> getAllLieux(
            @RequestHeader("Authorization") String authorizationHeader) {
        Response response = new Response();
        try {
            tokenService.checkSansRole(authorizationHeader);
            response.setStatus_code("200");
            response.setData(lieuService.getAllLieux());
            response.setMessage("réussi");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (TokenException e) {
            response.setStatus_code(e.getStatus_code());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, e.getStatus());
        }

    }

}
