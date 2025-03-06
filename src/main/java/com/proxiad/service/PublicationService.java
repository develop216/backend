package com.proxiad.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxiad.beans.Publication;
import com.proxiad.repo.PublicationRepository;

import java.util.List;

@Service
public class PublicationService {

    @Autowired
    private PublicationRepository publicationRepository;

    // Méthode pour créer une publication
    public Publication createPublication(Publication publication) {
    	System.out.println("publication : " + publication.getTitre());
        return publicationRepository.save(publication);
    }

    // Méthode pour récupérer toutes les publications
    public List<Publication> getAllPublications() {
        return publicationRepository.findAllByOrderByDateCreationDesc();
    }
    
    public Publication getPublicationById(int id) {
    	return publicationRepository.findPublicationById(id);
    }
    
    public List<Publication> getPublicationsByUserId(int utilisateurId) {
    	return publicationRepository.findPublicationsByUtilisateurId(utilisateurId);
    }

}