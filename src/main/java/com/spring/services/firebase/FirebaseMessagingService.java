package com.spring.services.firebase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import java.util.Map;
import java.util.HashMap;

@Service
public class FirebaseMessagingService {

        
        @Autowired
        private FirebaseMessaging firebaseMessaging;
        
        public String sendNotification(String contenu, String tokenMobile) {
                String title = "Nouveau message";

                // Déclaration de la Map
                Map<String, String> data = new HashMap<>();

                // Ajout de deux paires clé-valeur
                data.put("clé1", "valeur1");
                data.put("clé2", "valeur2");

                Notification notification = Notification
                        .builder()
                        .setTitle(title)
                        .setBody(contenu)
                        .build();
                
                Message message = Message
                        .builder()
                        .setToken(tokenMobile)
                        .setNotification(notification)
                        .putAllData(data)
                        .build();

                try {
                        System.out.println("** " + firebaseMessaging.send(message) + " **");
                        return "Success Sending Notification";
                } catch(Exception e) {
                        e.printStackTrace();
                }
                return "Error Sending Notification";
        }
}
