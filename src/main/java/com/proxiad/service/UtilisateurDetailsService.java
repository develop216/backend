package com.proxiad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.proxiad.beans.Utilisateur;
import com.proxiad.repo.UtilisateurRepository;

@Service
public class UtilisateurDetailsService implements UserDetailsService {

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		 Utilisateur utilisateur = utilisateurRepository.getUtilisateurByEmail(email);
	        if (utilisateur == null) {
	            System.out.println("User Not Found");
	            throw new UsernameNotFoundException("user not found");
	        }
	        return utilisateur;
	}
	

}
