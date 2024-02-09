package com.spring.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "discussions")
public class Discussion {
    @Id
    private String id;
    private List<String> participants;
    private List<String> nomparticipants;
    private List<Message> messages;

    public Discussion() {
    }

    public Discussion(String participant1, Utilisateur participant2, Message message) {
        this.participants = new ArrayList<>();
        this.nomparticipants = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.participants.add(participant1);
        this.participants.add(participant2.getId_utilisateur().toString());
        this.nomparticipants.add(message.getEmetteur());
        this.nomparticipants.add(participant2.getNom() + " " + participant2.getPrenom());
        this.messages.add(message);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public List<String> getNomparticipants() {
        return nomparticipants;
    }

    public void setNomparticipants(List<String> nomparticipants) {
        this.nomparticipants = nomparticipants;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

}
