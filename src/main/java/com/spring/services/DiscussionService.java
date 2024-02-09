package com.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.models.Annonce;
import com.spring.models.Discussion;
import com.spring.models.Message;
import com.spring.models.Utilisateur;
import com.spring.repository.DiscussionRepository;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DiscussionService {

    private final DiscussionRepository discussionRepository;

    @Autowired
    public DiscussionService(DiscussionRepository discussionRepository) {
        this.discussionRepository = discussionRepository;
    }

    public List<Discussion> getAllDiscussions() {
        return discussionRepository.findAll();
    }

    public Discussion getDiscussionById(String id) {
        return discussionRepository.findById(id).orElse(null);
    }

    public Discussion saveDiscussion(Discussion discussion) {
        return discussionRepository.save(discussion);
    }

    public void deleteDiscussion(String id) {
        discussionRepository.deleteById(id);
    }

    private void removeuserActive(Discussion discussion, String user) {
        int id = -1;
        for (int i = 0; i < discussion.getParticipants().size(); i++) {
            if (discussion.getParticipants().get(i).equals(user)) {
                discussion.getParticipants().remove(i);
                id = i;
                break;
            }
        }
        discussion.getNomparticipants().remove(id);
    }

    public List<Discussion> getUserDiscussions(String participant) {
        Sort sort = Sort.by(Sort.Order.desc("messages.date"));
        List<Discussion> rep = discussionRepository.findUserDiscussions(participant, sort);
        for (Discussion discussion : rep) {
            removeuserActive(discussion, participant);
            discussion.setMessages(new ArrayList<>());
        }
        return rep;
    }

    public List<Discussion> getPrivateDiscussion(String participant1, String participant2) {
        return discussionRepository.findPrivateDiscussion(participant1, participant2);
    }

    public void addMessageToDiscussion(String participant1, Utilisateur user, Message message) {
        List<Discussion> discussions = discussionRepository.findPrivateDiscussion(participant1,
                user.getId_utilisateur().toString());

        if (discussions.size() != 0) {
            Discussion discussion = discussionRepository
                    .findPrivateDiscussion(participant1, user.getId_utilisateur().toString()).get(0);
            discussion.getMessages().add(message);
            discussionRepository.save(discussion);
            System.out.println("** Discussion found **");
        } else {
            Discussion newDiscussion = new Discussion(participant1, user, message);
            discussionRepository.save(newDiscussion);
            System.out.println("** Discussion not found: " + participant1 + "|" + user.getId_utilisateur().toString());
        }
    }
}
