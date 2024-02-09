package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.models.notification.NotificationMessage;
import com.spring.services.firebase.FirebaseMessagingService;
import com.spring.utility.Response;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/notif")
@CrossOrigin
public class TestNotification {

    @Autowired
    private FirebaseMessagingService firebaseBaseService;

    /*
     * @PostMapping("v1/test")
     * public ResponseEntity<Response> testNotif(@RequestBody NotificationMessage
     * notif) {
     * Response response = new Response();
     * try {
     * 
     * response.setStatus_code("200");
     * response.setData(firebaseBaseService.sendNotification(notif));
     * response.setMessage("réussi");
     * 
     * } catch (FirebaseMessagingException e) {
     * // TODO Auto-generated catch block
     * e.printStackTrace();
     * response.setData(e.getMessage());
     * }
     * return new ResponseEntity<>(response, HttpStatus.OK);
     * }
     */

}
