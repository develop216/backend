package com.carrefour.kata;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.carrefour.delivery.Customer;
import com.carrefour.delivery.DeliveryMethod;
import com.carrefour.delivery.DeliverySlots;

@SpringBootApplication
public class KataApplication {

	public static void main(String[] args) {
		SpringApplication.run(KataApplication.class, args);
		Customer customer = new Customer();
		DeliverySlots deliverySlots = new DeliverySlots("creneaux.txt");
		// Initialisation des créneaux
		deliverySlots.afficherCreneaux();
		
		// Reservation pour un creneau et un mode de livraison
		customer.chooseDeliveryMethod(DeliveryMethod.DELIVERY);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime slot = LocalDateTime.parse("2024-05-30 10:00", formatter);
		customer.chooseDeliveryTimeSlot(slot);
		deliverySlots.bookSlot(slot, DeliveryMethod.DELIVERY);
		
		//Affichage des nouveaux créneaux
		deliverySlots.afficherCreneaux();

	}

}
