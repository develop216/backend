package com.proxiad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proxiad.beans.Publication;
import com.proxiad.service.PublicationService;

import java.util.List;

@RestController
@RequestMapping("/api/publications")
@CrossOrigin(origins="http://localhost:4200")
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    @PostMapping
    public ResponseEntity<Publication> createPublication(@RequestBody Publication publication) {
        Publication createdPublication = publicationService.createPublication(publication);
        return new ResponseEntity<>(createdPublication, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Publication>> getAllPublications() {
        List<Publication> publications = publicationService.getAllPublications();
        return new ResponseEntity<>(publications, HttpStatus.OK);
    }
    
    
    @GetMapping("/utilisateur/{utilisateurId}") 
    public ResponseEntity<List<Publication>> getPublicationByUserId(@PathVariable int utilisateurId){
    	List<Publication> publications = publicationService.getPublicationsByUserId(utilisateurId);
    	return new ResponseEntity<>(publications, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Publication> getPublicationById(@PathVariable int id){
    	Publication publication = publicationService.getPublicationById(id);
    	return new ResponseEntity<>(publication, HttpStatus.OK);   	
    }
    
    

 }