package com.spring.services.view;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.models.view.AnnonceStatView;
import com.spring.repository.view.AnnonceStatViewRepository;

@Service
public class AnnonceStatViewService {

    @Autowired
    private AnnonceStatViewRepository annonceStatViewRepository;

    public List<AnnonceStatView> getAllAnnonceStatView() {
        return annonceStatViewRepository.findAll();
    }

    public List<AnnonceStatView> getAllAnnonceStatOfMonth(Long mois, Long annee) {
        return annonceStatViewRepository.findByMoisAndAnnee(mois, annee);
    }


    public Optional<AnnonceStatView> getAnnonceStatViewById(Long id) {
        return annonceStatViewRepository.findById(id);
    }
    
}
