package com.proxiad.controller;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins="*")
public class AuthController {
	
	 private final JwtEncoder encoder;
	 
	 
	 public AuthController(JwtEncoder encoder) {
	        this.encoder = encoder;
	    }
	 
	 
	 
	@PostMapping("")
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
    }
}