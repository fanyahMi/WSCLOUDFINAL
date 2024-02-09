package com.spring.controller;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.exception.TokenException;
import com.spring.models.Annonce;
import com.spring.models.Discussion;
import com.spring.models.Message;
import com.spring.models.Utilisateur;
import com.spring.services.DiscussionService;
import com.spring.services.TokenMobileService;
import com.spring.services.UtilisateurService;
import com.spring.services.firebase.FirebaseMessagingService;
import com.spring.services.TokenService;
import com.spring.token.Token;
import com.spring.token.TokenMobile;
import com.spring.utility.Response;

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/api/v1/discussions")
@CrossOrigin
public class DiscussionController {

    @Autowired
    private DiscussionService discussionService;
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private FirebaseMessagingService notificationService;
    @Autowired
    private TokenMobileService tokenMobileService;
    @Autowired
    private TokenService tokenService;

    @GetMapping("/prive")
    public ResponseEntity<Response> getPrivateDiscussion(@RequestParam String participant2,
            @RequestHeader("Authorization") String authorizationHeader) {
        Response response = new Response();
        try {
            Claims claims = tokenService.getClaims(authorizationHeader);
            String user_id = claims.get("idUtilisateur").toString();

            tokenService.checkSansRole(authorizationHeader);
            response.setStatus_code("200");
            response.setData(discussionService.getPrivateDiscussion(user_id, participant2));
            response.setMessage("réussi");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (TokenException e) {
            response.setStatus_code(e.getStatus_code());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, e.getStatus());
        }
        // return discussionService.getPrivateDiscussion(user_id, participant2);
    }

    @GetMapping
    public ResponseEntity<Response> getUserDiscussions(@RequestHeader("Authorization") String authorizationHeader) {
        Response response = new Response();
        try {
            tokenService.checkSansRole(authorizationHeader);

            Claims claims = tokenService.getClaims(authorizationHeader);
            String user_id = claims.get("idUtilisateur").toString();
            response.setStatus_code("200");
            response.setData(discussionService.getUserDiscussions(user_id));
            response.setMessage("réussi");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (TokenException e) {
            response.setStatus_code(e.getStatus_code());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, e.getStatus());
        }
    }

    @PostMapping("/message/envoye")
    public ResponseEntity<Response> addMessageToDiscussion(@RequestParam String participant2,
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody Message message) {
        Response response = new Response();
        try {
            tokenService.checkSansRole(authorizationHeader);
            Claims claims = tokenService.getClaims(authorizationHeader);

            Utilisateur user = utilisateurService.getUtilisateurById(Long.parseLong(participant2));

            if (user == null) {
                throw new TokenException("Désolé. Adresse du récépteur invalide ou inexistant", "403",
                        HttpStatus.FORBIDDEN);
            }

            // Envoye notification vers android a partir de firebase
            TokenMobile tokenMobile = tokenMobileService
                    .findByUtilisateurId(Long.parseLong(participant2));
            if (tokenMobile != null) {
                System.out.println("** TokenMobile: " + tokenMobile.getTokenmobile() + " **");
                notificationService.sendNotification(message.getContenu(), tokenMobile.getTokenmobile()); //
                // Miantso firebase handefa mobile
            } else {
                System.out.println("** TokenMobile is null **");
            }

            String userid = claims.get("idUtilisateur").toString();
            message.setEmetteur(claims.get("nomPrenom").toString());
            message.setDate(new Date(System.currentTimeMillis()));
            message.setIdemetteur(userid);
            discussionService.addMessageToDiscussion(userid, user, message);
            response.setStatus_code("200");
            response.setData(discussionService.getPrivateDiscussion(userid, participant2));
            response.setMessage("réussi");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (TokenException e) {
            response.setStatus_code(e.getStatus_code());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, e.getStatus());
        }
        // discussionService.addMessageToDiscussion(user_id, participant2, message);
    }
}
