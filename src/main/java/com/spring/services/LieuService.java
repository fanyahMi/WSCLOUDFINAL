package com.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.models.Lieu;
import com.spring.repository.LieuRepository;

import java.util.Optional;

@Service
public class LieuService {

    private final LieuRepository lieuRepository;

    @Autowired
    public LieuService(LieuRepository lieuRepository) {
        this.lieuRepository = lieuRepository;
    }

    public List<Lieu> getAllLieux() {
        return lieuRepository.findAll();
    }

    public Lieu getLieuById(Long id) {
        Optional<Lieu> optionalLieu = lieuRepository.findById(id);
        return optionalLieu.orElse(null);
    }

    public Lieu createLieu(Lieu lieu) {
        return lieuRepository.save(lieu);
    }

    public Lieu updateLieu(Long id, Lieu updatedLieu) {
        Optional<Lieu> optionalLieu = lieuRepository.findById(id);
        if (optionalLieu.isPresent()) {
            Lieu existingLieu = optionalLieu.get();
            existingLieu.setLieu(updatedLieu.getLieu());
            return lieuRepository.save(existingLieu);
        } else {
            return null;
        }
    }

    public void deleteLieu(Long id) {
        lieuRepository.deleteById(id);
    }
}
