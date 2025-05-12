package com.proxiad.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proxiad.beans.Utilisateur;
import com.proxiad.repo.UtilisateurRepository;

@Service
public class UtilisateurService {
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	AuthenticationManager authManager;

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private UtilisateurDetailsService utilisateurDetailsService;

	

	 
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	 public UtilisateurService(UtilisateurRepository utilisateurRepository) {
		 this.utilisateurRepository = utilisateurRepository;
	 }
	
	public Utilisateur getUtilisateurById(int id) {
		return utilisateurRepository.getUtilisateurById(id);
	}
	
//	public Optional<Utilisateur> getUtilisateurByEmail(String email) {
//		return utilisateurRepository.getUtilisateurByEmail(email);
//	}

	

	   public void saveUtilisateur(Utilisateur utilisateur) {
	        utilisateurRepository.save(utilisateur);
	    }
	   
	   
	  

	  /*  public String verify(Utilisateur utilisateur) {
	        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(utilisateur.getUsername(), utilisateur.getPassword()));
	        if (authentication.isAuthenticated()) {
	            return jwtService.generateToken(utilisateur.getUsername());
	        } else {
	            return "fail";
	        }
	    }
	    */

	    public String verify(Utilisateur utilisateur) {
	        Authentication authentication = authManager.authenticate(
	            new UsernamePasswordAuthenticationToken(utilisateur.getUsername(), utilisateur.getPassword())
	        );

	        if (authentication.isAuthenticated()) {
	            UserDetails userDetails = utilisateurDetailsService.loadUserByUsername(utilisateur.getUsername());
	            return jwtService.generateToken(userDetails);
	        } else {
	            return "fail";
	        }
	    }
	   
	   private List<GrantedAuthority> getGrantedAuthorities(String role) {
	        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
	        return authorities;
	    }


	   
	  

	
}
