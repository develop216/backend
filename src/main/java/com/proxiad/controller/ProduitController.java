package com.proxiad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proxiad.model.Produit;
import com.proxiad.service.ProduitService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class ProduitController {
	
	  	@Autowired
	    private ProduitService produitService;
	  	
	  	public ProduitController(ProduitService produitService) {
	        this.produitService = produitService;
	    }

	    @GetMapping("/produit")
	    public List<Produit> getAllProduits() {
	        return produitService.getAllProduits();
	    }

	    @PostMapping
	    public Produit ajouterProduit(@RequestBody Produit produit) {
	        return produitService.saveProduit(produit);
	    }
	    
	    
}

