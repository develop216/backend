package com.proxiad.beans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="produit")
public class Produit {
    
    @Id
    private int id;
    
    
    @Column(name = "nom")
    private String nom;
    
    @Column(name = "quantite")
    private int quantite;
    
    
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	  @Override
	    public String toString() {
	        return "Produit{" +
	                "id=" + id +
	                ", nom='" + nom + '\'' +
	                ", quantite=" + quantite +
	                '}';
	    }
	
}