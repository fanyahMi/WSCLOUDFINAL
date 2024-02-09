package com.spring.services.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.models.view.VenteView;
import com.spring.repository.view.VenteViewRepository;

@Service
public class VenteViewService {

    @Autowired
    private VenteViewRepository venteViewRepository;

    public List<VenteView> getAllVenteView() {
        return venteViewRepository.findAll();
    }
    
    public List<VenteView> getVenteViewOfMonth(Long mois, Long annee) {
        return venteViewRepository.findByMoisAndAnneeOrderByPrixAchatDesc(mois, annee);
    }
}
