package com.spring.services;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.connexion.Connexion_projet;
import com.spring.models.Categorie;
import com.spring.models.view.CategorieMarqueView;
import com.spring.repository.CategorieRepository;

@Service
public class CategorieService {
    @Autowired
    private CategorieRepository categorieRepository;

    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    public Optional<Categorie> getCategoryById(Integer id) {
        return categorieRepository.findById(id);
    }

    public Categorie saveCategory(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    public void deleteCategory(Integer id) {
        categorieRepository.deleteById(id);
    }

    /****
     * Liste marque par categori
     * 
     * @throws Exception
     ***/
    public List<CategorieMarqueView> getListeMarqueCategorie(Categorie categorie) throws Exception {
        Connection c = null;
        try {
            c = new Connexion_projet().getconnection();
            CategorieMarqueView detail = new CategorieMarqueView();
            detail.setIdCategorie(categorie.getIdCategorie());
            return detail.findAll(c);
        } catch (Exception e) {
            throw e;
        } finally {
            if (c != null)
                c.close();
        }
    }

    public Categorie updateCategory(Integer id, Categorie updatedCategorie) {
        Categorie existingCategory = categorieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));
        existingCategory.setCategorie(updatedCategorie.getCategorie());
        return categorieRepository.save(existingCategory);
    }
}