package com.carrefour.delivery;

import java.time.LocalDateTime;

public class Customer {
	    private DeliveryMethod deliveryMethod;
	    private LocalDateTime deliveryTimeSlot;

	    // Méthode pour choisir la méthode de livraison
	    public void chooseDeliveryMethod(DeliveryMethod method) {
	        this.deliveryMethod = method;
	    }

	    // Méthode pour choisir le créneau horaire de livraison
	    public void chooseDeliveryTimeSlot(LocalDateTime timeSlot) {
	        this.deliveryTimeSlot = timeSlot;
	    }

	    public DeliveryMethod getDeliveryMethod() {
	        return this.deliveryMethod;
	    }

	    public LocalDateTime getDeliveryTimeSlot() {
	        return this.deliveryTimeSlot;
	    }

}
