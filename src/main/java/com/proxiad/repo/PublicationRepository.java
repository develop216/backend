package com.proxiad.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proxiad.beans.Publication;

import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
    
    List<Publication> findAllByOrderByDateCreationDesc(); // Pour récupérer les publications dans l'ordre chronologique
    Publication findPublicationById(int id);
    List<Publication> findPublicationsByUtilisateurId(int utilisateurId);

}