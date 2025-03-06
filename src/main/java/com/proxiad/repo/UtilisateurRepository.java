package com.proxiad.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proxiad.beans.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{

	public Utilisateur getUtilisateurById(int id);
	//public Utilisateur getUtilisateurByEmail(String email);
	public Optional<Utilisateur> getUtilisateurByEmail(String email);
}
