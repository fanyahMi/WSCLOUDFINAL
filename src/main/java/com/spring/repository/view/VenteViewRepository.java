package com.spring.repository.view;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.spring.models.view.VenteView;
import org.springframework.data.jpa.repository.Query;

public interface VenteViewRepository extends JpaRepository<VenteView, Long> {
    // List<VenteView> findByMoisAndAnneeOrderByPrix_AchatDesc(Long mois, Long annee);

    // Si vous avez besoin d'une requête native SQL avec tri, vous pouvez utiliser Query
    @Query("SELECT v FROM VenteView v WHERE v.mois = :mois AND v.annee = :annee ORDER BY v.prix_achat DESC")
    List<VenteView> findByMoisAndAnneeOrderByPrixAchatDesc(@Param("mois") Long mois, @Param("annee") Long annee);

}
