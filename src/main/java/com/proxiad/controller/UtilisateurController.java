package com.proxiad.controller;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proxiad.beans.Utilisateur;
import com.proxiad.service.UtilisateurService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@RestController
@RequestMapping("/api/utilisateur")
@CrossOrigin(origins="*")
public class UtilisateurController {

	@Autowired
	private UtilisateurService utilisateurService;
	
	
	private final String secretKey = "secretKey";


	
	private String generateToken(Utilisateur utilisateur) {
        return Jwts.builder()
                .setSubject(utilisateur.getUsername())
                .claim("id", utilisateur.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000)) // Expires in 10 minutes
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
	
	private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
	
}
