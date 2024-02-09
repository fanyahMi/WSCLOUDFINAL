package com.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.models.Comission;
import com.spring.repository.ComissionRepository;

@Service
public class ComissionService {
    @Autowired
    private ComissionRepository comissionRepository;

    public Comission saveComission(Comission comission) {
        return comissionRepository.save(comission);
    }

    public Long getComissionlast() {
        if (comissionRepository == null)
            return 6L;
        return comissionRepository.findLatestComission();
    }

    public List<Comission> getListCommission() {
        return comissionRepository.listCommissions();
    }
}
