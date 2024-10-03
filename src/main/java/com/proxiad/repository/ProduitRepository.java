package com.proxiad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proxiad.model.Produit;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
	
	public List<Produit> findAll();
    public long count();
}