package com.proxiad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proxiad.beans.Produit;
import com.proxiad.repo.ProduitRepository;

@Service
public class ProduitService {

	    private ProduitRepository produitRepository;
	    
	    public ProduitService(ProduitRepository produitRepository) {
	    	this.produitRepository = produitRepository;
	    }

	 	@Transactional
	    // Méthode pour récupérer tous les produits
	    public List<Produit> getAllProduits() {
	        return produitRepository.findAll();
	    }
	 	
	 	public Produit getProduitTest() {
	 		Produit produit = new Produit();
	 		produit.setId(345);
	 		produit.setNom("Sac");
	 		produit.setQuantite(4);
	 		return produit;
	 	}
	 	

	 	public void saveProduit(Produit produit) {
	        produitRepository.save(produit);
	    }


	    public Produit getProduitById(long id) {
	        return produitRepository.findById(id).orElse(null);
	    }

	    public void deleteProduit(long id) {
	        produitRepository.deleteById(id);
	    }
	
}
