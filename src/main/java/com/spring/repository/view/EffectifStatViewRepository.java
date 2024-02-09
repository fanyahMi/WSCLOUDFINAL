package com.spring.repository.view;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.models.view.EffectifStatView;

public interface EffectifStatViewRepository extends JpaRepository<EffectifStatView, Long> {
    
}
