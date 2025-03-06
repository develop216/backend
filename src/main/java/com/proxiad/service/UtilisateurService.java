package com.proxiad.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proxiad.beans.Utilisateur;
import com.proxiad.repo.UtilisateurRepository;

@Service
public class UtilisateurService implements UserDetailsService{

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	

	 
	 public UtilisateurService(UtilisateurRepository utilisateurRepository) {
		 this.utilisateurRepository = utilisateurRepository;
	 }
	
	public Utilisateur getUtilisateurById(int id) {
		return utilisateurRepository.getUtilisateurById(id);
	}
	
	public Optional<Utilisateur> getUtilisateurByEmail(String email) {
		return utilisateurRepository.getUtilisateurByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		 Optional<Utilisateur> utilisateur = utilisateurRepository.getUtilisateurByEmail(email);
	        if (utilisateur.isEmpty()) {
	            throw new UsernameNotFoundException("Utilisateur non trouvé avec l'email: " + email);
	        }
	        return utilisateur.get();
	}

	   public void saveUtilisateur(Utilisateur utilisateur) {
	        utilisateurRepository.save(utilisateur);
	    }
	   
	   private List<GrantedAuthority> getGrantedAuthorities(String role) {
	        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
	        return authorities;
	    }
	   
	  

	
}
