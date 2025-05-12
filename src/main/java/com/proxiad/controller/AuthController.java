package com.proxiad.controller;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtClaimsSet.Builder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proxiad.beans.Utilisateur;
import com.proxiad.config.JwtUtils;
import com.proxiad.repo.UtilisateurRepository;
import com.proxiad.service.UtilisateurService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
		@Autowired
		private UtilisateurService service;

	    
	 /*   @PostMapping("/register")
	    public ResponseEntity<?> register(@RequestBody Utilisateur utilisateur) {
	        if (utilisateurRepository.getUtilisateurByEmail(utilisateur.getEmail()) != null) {
	            return ResponseEntity.badRequest().body("Username is already in use");
	        }
	        utilisateur.setPasswordHash(passwordEncoder.encode(utilisateur.getPassword()));
	        return ResponseEntity.ok(utilisateurRepository.save(utilisateur));
	    }*/

	    @PostMapping("/login")
	    public String login(@RequestBody Utilisateur utilisateur) {
	        /*Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,  password));
	        Instant instant = Instant.now();
	        String scope = authentication.getAuthorities().stream()
	        		.map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
	        JwtClaimsSet jwtClaimsSet= JwtClaimsSet.builder()
	        		.issuedAt(instant)
	        		.expiresAt(instant.plus(10, ChronoUnit.MINUTES))
	        		.subject(email)
	        		.claim("scope", scope)
	        		.build();
	        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(
	        				JwsHeader.with(MacAlgorithm.HS512).build(),
	        				jwtClaimsSet			
	        );
	        String jwt = jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
	        return Map.of("access-token", jwt);*/
	    	return service.verify(utilisateur);
	    }
	    
	    
	    
	/*@PostMapping("")
	public String auth(Authentication authentication) {
		Instant now = Instant.now();
	    long expiry = 36000L;
	    String scope = authentication.getAuthorities().stream()
	    		.map(GrantedAuthority::getAuthority)
	            .collect(Collectors.joining(","));
	    JwtClaimsSet claims = JwtClaimsSet.builder()
	    		.issuer("self")
	            .issuedAt(now)
	            .expiresAt(now.plusSeconds(expiry))
	            .subject(authentication.getName())
	            .claim("scope", scope)
	            .build();
	    return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	    }
	

    @GetMapping("/user")
    public String login() {
        return "Welcome, User";
    }

    @GetMapping("/admin")
    public String logout() {
        return "Welcome, Admin";
    }*/
	

	
}