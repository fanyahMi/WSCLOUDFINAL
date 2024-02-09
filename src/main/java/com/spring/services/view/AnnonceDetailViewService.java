package com.spring.services.view;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.models.view.AnnonceDetailView;
import com.spring.repository.view.AnnonceDetailViewRepository;

@Service
public class AnnonceDetailViewService {

    @Autowired
    private AnnonceDetailViewRepository annonceDetailViewRepository;

    public List<AnnonceDetailView> getAllAnnonceDetailView() {
        return annonceDetailViewRepository.findAll();
    }

    public Optional<AnnonceDetailView> getAnnonceDetailViewById(Long id) {
        return annonceDetailViewRepository.findById(id);
    }
    
}
