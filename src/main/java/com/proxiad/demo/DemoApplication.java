package com.proxiad.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.proxiad.beans.Produit;
import com.proxiad.service.ProduitService;

@SpringBootApplication(scanBasePackages={"com.proxiad"})
@EnableJpaRepositories(basePackages = "com.proxiad.repo") 
@EntityScan("com.proxiad.beans") 
public class DemoApplication {
	
	@Autowired
	private ProduitService produitService; 

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)  // S'exécute après le démarrage de l'application
    public void displayProduits() {
        List<Produit> produits = produitService.getAllProduits();
        System.out.println("product list" + produitService.getProduitTest());
//        for (Produit produit : produits) {
//            System.out.println(produit.toString());
//        }
        
    }

}
