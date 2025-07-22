package com.aurionpro.model;

public class PaymentProcess {
	public boolean paymentProcess(String mode , double amount) {
		System.out.println("processing payment of: "+ amount +" via" + mode);
		return true;
	}
}
