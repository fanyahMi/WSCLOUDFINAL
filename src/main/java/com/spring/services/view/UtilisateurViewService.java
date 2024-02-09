package com.spring.services.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.models.view.UtilisateurView;
import com.spring.repository.view.UtilisateurViewRepository;

@Service
public class UtilisateurViewService {

    @Autowired
    private UtilisateurViewRepository utilisateurViewRepository;

    public List<UtilisateurView> getAllUtilisateurView() {
        return utilisateurViewRepository.findAll();
    }
}
