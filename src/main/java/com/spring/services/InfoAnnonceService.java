package com.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.spring.models.FiltreAnnonce;
import com.spring.models.InfoAnnonce;
import com.spring.repository.InfoAnnonceRepository;

import java.util.List;

@Service
public class InfoAnnonceService {

    private final InfoAnnonceRepository infoAnnonceRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public InfoAnnonceService(InfoAnnonceRepository infoAnnonceRepository, MongoTemplate mongoTemplate) {
        this.infoAnnonceRepository = infoAnnonceRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public List<InfoAnnonce> getAllInfoAnnonces() {
        return infoAnnonceRepository.findAll();
    }

    public List<InfoAnnonce> getInfoAnnonceByFiltre(FiltreAnnonce filtreAnnonce) {
        Query query = new Query();
        filtreAnnonce.setQueryCriteria(query);

        return mongoTemplate.find(query, InfoAnnonce.class);
    }

    public void updateStatutByAnnonceId(String annonceId, Long status) {
        Query query = new Query(Criteria.where("annonce_id").is(annonceId));
        Update update = Update.update("statut", status);
        mongoTemplate.updateFirst(query, update, InfoAnnonce.class);
    }

    public void addToFavoris(String annonceId, String utilisateurId) {
        Query query = new Query(Criteria.where("annonce_id").is(annonceId));
        Update update = new Update().push("favoris", utilisateurId);
        mongoTemplate.updateFirst(query, update, InfoAnnonce.class);
    }

    public List<InfoAnnonce> getAllAuthorizedAnnonces() {
        return infoAnnonceRepository.findByStatutEquals(2);
    }

    public List<InfoAnnonce> getAllUnsoldAnnonce() {
        return infoAnnonceRepository.findByStatutInferiorTo(3);
    }

    public List<InfoAnnonce> getAllAuteurAnnonces(String auteur_id) {
        return infoAnnonceRepository.findByAuteurId(auteur_id);
    }

    public List<InfoAnnonce> getAllAuteurFavoris(String auteur_id) {
        return infoAnnonceRepository.findUserFavoris(auteur_id);
    }

    public InfoAnnonce getInfoAnnonceById(String annonce_id) {
        return infoAnnonceRepository.findById(annonce_id).orElse(null);
    }

    public InfoAnnonce getInfoAnnonceByAnnonceId(String annonce_id) {
        return infoAnnonceRepository.findByAnnonceId(annonce_id).get(0);
    }

    public InfoAnnonce saveInfoAnnonce(InfoAnnonce infoAnnonce) {
        return infoAnnonceRepository.save(infoAnnonce);
    }

    public InfoAnnonce updateInfoAnnonce(InfoAnnonce newInfoAnnonce) {
        InfoAnnonce infoannonce = infoAnnonceRepository.findByAnnonceId(newInfoAnnonce.getAnnonce_id().toString())
                .get(0);

        infoannonce.updateTo(newInfoAnnonce);

        return infoAnnonceRepository.save(infoannonce);
    }

    public void deleteInfoAnnonce(String annonce_id) {
        infoAnnonceRepository.deleteByAnnonceId(annonce_id);
    }
}
