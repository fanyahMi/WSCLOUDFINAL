package com.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.models.Vente;
import com.spring.repository.VenteRepository;

@Service
public class VenteService {
    @Autowired
    private VenteRepository venteRepository;

    public Vente newVente(Vente vente) {
        return venteRepository.save(vente);
    }
}
