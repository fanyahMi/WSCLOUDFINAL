package com.spring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.models.Annonce;
import com.spring.models.Voiture;
import com.spring.repository.VoitureRepository;

@Service
public class VoitureService {
    @Autowired
    private VoitureRepository voitureRepository;

    public Long getLatestId() {
        return voitureRepository.findMaxId();
    }

    public Voiture addVoiture(Voiture voiture) {
        return voitureRepository.save(voiture);
    }

    public Voiture getVoitureById(Long id) {
        return voitureRepository.findById(id).orElse(null);
    }

    public Voiture updateVoiture(Voiture newVoiture) {
        Optional<Voiture> optvoiture = voitureRepository.findById(newVoiture.getIdVoiture());

        if (optvoiture.isPresent()) {
            Voiture voiture = optvoiture.get();
            voiture.updateTo(newVoiture);

            return voitureRepository.save(voiture);
        }

        return null;
    }

    public void deleteVoiture(Long id) {
        voitureRepository.deleteById(id);
    }
}
