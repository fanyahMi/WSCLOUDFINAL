package com.spring.services.view;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.models.view.VenteStatView;
import com.spring.repository.view.VenteStatViewRepository;

@Service
public class VenteStatViewService {

    @Autowired
    private VenteStatViewRepository venteStatViewRepository;

    public List<VenteStatView> getAllVenteStatView() {
        return venteStatViewRepository.findAll();
    }

    public Optional<VenteStatView> getVenteStatViewById(Long id) {
        return venteStatViewRepository.findById(id);
    }
    
}
