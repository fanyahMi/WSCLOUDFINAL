package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.spring.models.Annonce;

public interface AnnonceRepository extends JpaRepository<Annonce, Long> {

    @Query("UPDATE Annonce SET statut = ?1, dateConfirmation = current_date WHERE idAnnonce = ?2")
    @Modifying
    @Transactional
    void authoriser(int statut, Long id);

    @Query("UPDATE Annonce SET statut = ?1 WHERE idAnnonce = ?2")
    @Modifying
    @Transactional
    void vendu(int statut, Long id);

    @Query("SELECT MAX(a.idAnnonce) FROM Annonce a")
    Long findMaxId();

    List<Annonce> findAllByVendeurId(long id);
}
