package com.spring.services.view;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.models.view.EffectifStatView;
import com.spring.repository.view.EffectifStatViewRepository;

@Service
public class EffectifStatViewService {

    @Autowired
    private EffectifStatViewRepository effectifStatViewRepository;

    public Optional<EffectifStatView> getEffectifStat() {
        Long id = (long) 1;
        return effectifStatViewRepository.findById(id);
    }

}
