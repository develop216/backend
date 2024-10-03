package com.proxiad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proxiad.model.Produit;
import com.proxiad.repository.ProduitRepository;

@Service
public class ProduitService {

	 	@Autowired
	    private ProduitRepository produitRepository;

	 	@Transactional
	    // Méthode pour récupérer tous les produits
	    public List<Produit> getAllProduits() {
	        return produitRepository.findAll();
	    }
	 	
	    // Méthode pour ajouter un produit
	 	@Transactional
	 	public Produit saveProduit(Produit produit) {
	        return produitRepository.save(produit);
	    }

	    // Méthode pour récupérer un produit par ID
	 	@Transactional
	    public Produit getProduitById(long id) {
	        return produitRepository.findById(id).orElse(null);
	    }

	    // Méthode pour supprimer un produit
	 	@Transactional
	    public void deleteProduit(long id) {
	        produitRepository.deleteById(id);
	    }
	
}
