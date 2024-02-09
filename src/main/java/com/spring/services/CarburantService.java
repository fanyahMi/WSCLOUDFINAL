package com.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.models.Carburant;
import com.spring.repository.CarburantRepository;

@Service
public class CarburantService {
    @Autowired
    private CarburantRepository carburantrepository;

    public List<Carburant> getAllCarburants() {
        return carburantrepository.findAll();
    }

    public Carburant saveCarburant(Carburant carburant) {
        return carburantrepository.save(carburant);
    }

    public void deleteCarburant(Long id) {
        carburantrepository.deleteById(id);
    }

    public Carburant updateCarburant(Long id, Carburant updatedCarburant) {
        Carburant existingCarburant = carburantrepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Le carburant n'est pas trouvée " + id));
        existingCarburant.setCarburant(updatedCarburant.getCarburant());
        return carburantrepository.save(existingCarburant);
    }
}
