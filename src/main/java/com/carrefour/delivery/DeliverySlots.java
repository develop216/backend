package com.carrefour.delivery;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

public class DeliverySlots {
	    private NavigableMap<LocalDateTime, Boolean> slots;

	    public DeliverySlots() {
	        this.slots = new TreeMap<>();
	    }

	    public DeliverySlots(String filename) {
	        this.slots = new TreeMap<>();
	        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
	        try {
	            Resource resource = resourceLoader.getResource("classpath:" + filename);
	            InputStream inputStream = resource.getInputStream();
	            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

	            String line;
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	            while ((line = bufferedReader.readLine()) != null) {
	                LocalDateTime slot = LocalDateTime.parse(line, formatter);
	                slots.put(slot, false);
	            }

	            bufferedReader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public void afficherCreneaux() {
	    	System.out.println("liste des créneaux : ");
	    	    for (Map.Entry<LocalDateTime, Boolean> entry : slots.entrySet()) {
	    	        System.out.println(entry.getKey() + " - " + (entry.getValue() ? "Réservé" : "Disponible"));
	    	    }
	    }
	    
	    // Méthode pour réserver un créneau horaire
	    public boolean bookSlot(LocalDateTime slot, DeliveryMethod method) {
	        if (method == DeliveryMethod.DELIVERY_TODAY && !LocalDateTime.now().toLocalDate().equals(slot.toLocalDate())) {
	            System.out.println("Erreur : Vous ne pouvez pas réserver un créneau pour aujourd'hui à une date ultérieure.");
	            return false;
	        }

	        if (method == DeliveryMethod.DELIVERY_ASAP) {
	            Map.Entry<LocalDateTime, Boolean> nextSlot = slots.ceilingEntry(LocalDateTime.now());
	            if (nextSlot != null && !nextSlot.getValue()) {
	                slots.put(nextSlot.getKey(), true);
	                return true;
	            } else {
	                System.out.println("Erreur : Il n'y a pas de créneau disponible pour une livraison immédiate.");
	                return false;
	            }
	        }

	        if (slots.get(slot) == null || !slots.get(slot)) {
	            slots.put(slot, true);
	            return true;
	        } else {
	            System.out.println("Erreur : Ce créneau est déjà réservé.");
	            return false;
	        }
	    }
	    
	    
	    

}
