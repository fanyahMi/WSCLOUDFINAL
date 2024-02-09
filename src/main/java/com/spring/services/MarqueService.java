package com.spring.services;

import java.sql.Connection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.connexion.Connexion_projet;
import com.spring.models.Marque;
import com.spring.models.view.CategorieMarqueView;
import com.spring.models.view.ModelDetailView;
import com.spring.repository.MarqueRepository;

@Service
public class MarqueService {

    @Autowired
    private MarqueRepository marqueRepository;

    public List<Marque> getAllMarques() {
        return marqueRepository.findAll();
    }

    public Marque getMarqueById(Integer id) {
        return marqueRepository.findById(id).orElse(null);
    }

    public Marque addMarque(Marque marque) {
        return marqueRepository.save(marque);
    }

    public Marque updateMarque(Integer id, Marque newMarque) {
        return marqueRepository.findById(id)
                .map(marque -> {
                    marque.setMarque(newMarque.getMarque());
                    return marqueRepository.save(marque);
                })
                .orElse(null);
    }

    public void deleteMarque(Integer id) {
        marqueRepository.deleteById(id);
    }

    /****
     * Liste categorie par marque
     * 
     * @throws Exception
     ***/
    public List<CategorieMarqueView> getListeCategorieMarque(Marque marque) throws Exception {
        Connection c = null;
        try {
            c = new Connexion_projet().getconnection();
            CategorieMarqueView detail = new CategorieMarqueView();
            detail.setId_marque(marque.getId_marque());
            return detail.findAll(c);
        } catch (Exception e) {
            throw e;
        } finally {
            if (c != null)
                c.close();
        }
    }

    /*** Liste Model par marque */
    public List<CategorieMarqueView> getListeModelMarque(Marque marque) throws Exception {
        Connection c = null;
        try {
            c = new Connexion_projet().getconnection();
            String[] col = { "id_marque", "marque", "id_model", "model" };
            ModelDetailView detail = new ModelDetailView();
            detail.setIdMarque(marque.getId_marque());
            return detail.find(c, col);
        } catch (Exception e) {
            throw e;
        } finally {
            if (c != null)
                c.close();
        }
    }

}
