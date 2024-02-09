package com.spring.services;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.connexion.Connexion_projet;
import com.spring.models.Boitevitesse;
import com.spring.models.Model;
import com.spring.models.view.ModelDetailAnneeView;
import com.spring.models.view.ModelDetailView;
import com.spring.repository.BoitevitesseRepository;
import com.spring.repository.ModelRepository;

@Service
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private BoitevitesseRepository boiteVitesseRepository;

    public List<Model> getAllModel() {
        return modelRepository.findAll();
    }

    public List<ModelDetailView> getListDetailModel(String marque_id, String categorie_id) throws Exception {
        Connection c = null;
        try {
            ModelDetailView model = new ModelDetailView();
            if (!marque_id.equals("")) {
                model.setIdMarque(marque_id);
            }
            if (!categorie_id.equals("")) {
                model.setIdCategorie(categorie_id);
            } else if (marque_id.equals("") && categorie_id.equals("")) {
                throw new Exception("Veuillez preciser la marque ou la catégorie");
            }
            c = new Connexion_projet().getconnection();
            return model.getListeModel(c);
        } catch (Exception e) {
            throw e;
        } finally {
            if (c != null)
                c.close();
        }

    }

    public List<ModelDetailAnneeView> getListAnneModel(Model model) throws Exception {
        Connection c = null;
        try {
            c = new Connexion_projet().getconnection();
            ModelDetailAnneeView detail = new ModelDetailAnneeView();
            detail.setIdModel(model.getId_model());
            return detail.findAll(c);
        } catch (Exception e) {
            throw e;
        } finally {
            if (c != null)
                c.close();
        }

    }

    public Optional<Model> findByModel(Integer idModel) {
        return modelRepository.findById(idModel);
    }

    public Model saveModel(Model model) {
        return modelRepository.save(model);
    }

    public void deleteModel(Integer id) {
        modelRepository.deleteById(id);
    }

    /****
     * Liste boite de vitessed
     * 
     * @throws Exception
     ****/
    public List<Boitevitesse> getListeBoiteVitesse() throws Exception {
        return boiteVitesseRepository.findAll();
    }

}
