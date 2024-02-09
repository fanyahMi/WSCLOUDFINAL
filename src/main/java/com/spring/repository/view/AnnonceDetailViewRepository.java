package com.spring.repository.view;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.models.view.AnnonceDetailView;

public interface AnnonceDetailViewRepository extends JpaRepository<AnnonceDetailView, Long> {
    
}
