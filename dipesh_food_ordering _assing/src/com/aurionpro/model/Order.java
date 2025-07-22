package com.aurionpro.model;
//Order: Contains order details (items, quantities, total, discount, payment mode, delivery partner).

import java.util.HashMap;
import java.util.Map;

public class Order {
	private Map<FoodItem, Integer> items = new HashMap<>();
	private int quantity;
	private double total;
	private double discount;
	private String paymentMode;
	private DelivaryPartner delivaryPartner;

	public Map<FoodItem, Integer> getItems() {
		return items;
	}

	public void setItems(Map<FoodItem, Integer> items) {
		this.items = items;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount2) {
		this.discount = discount2;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public DelivaryPartner getDelivaryPartner() {
		return delivaryPartner;
	}

	public void setDelivaryPartner(DelivaryPartner delivaryPartner) {
		this.delivaryPartner = delivaryPartner;
	}
	
	public void addItems(FoodItem item , int quantity) {
		items.put(item, items.getOrDefault(item, 0)+quantity);
	}
	
	public void removeItemFromOrder(FoodItem item) {
        if (items.containsKey(item)) {
            items.remove(item);
            System.out.println(item.getName() + " removed from your order.");
        } else {
            System.out.println("Item not found in your order.");
        }
    }
	
	  public void recalculateTotal() {
	        double currentTotal = 0;
	        for (Map.Entry<FoodItem, Integer> entry : items.entrySet()) {
	            currentTotal += entry.getKey().getPrice() * entry.getValue();
	        }
	        this.total = currentTotal;
	    }

	
//	public void invoice(Order order) {
//		System.out.println("\n---Invoice---");
//		for(Map.Entry<FoodItem, Integer> entry : order.getItems().entrySet()) {
//			FoodItem item = entry.getKey();
//			int qty = entry.getValue();
//			System.out.println(item.getName() + " x"+qty+ " - " +item.getPrice());
//		}
//		System.out.println("\n---Breakdown----");
//		System.out.println("Discount: " + order.getDiscount());
//		System.out.println("Payment Mode: " + order.getPaymentMode());
//		System.out.println("Delivary Partner: " + order.getDelivaryPartner());
//	}
	  public void invoice(Order order) {
	        System.out.println("\n---- Invoice ----");
	        recalculateTotal();

	        for (Map.Entry<FoodItem, Integer> entry : getItems().entrySet()) {
	            FoodItem item = entry.getKey();
	            int qty = entry.getValue();
	            System.out.println("Item: " + item.getName() + " x " + qty + " - RS" + (item.getPrice() * qty));
	        }
	        System.out.println("---");
	        double discount = (getTotal() > 500) ? getTotal() * 0.10 : 0;
	        setDiscount(discount);

	        System.out.println("Subtotal: RS" + getTotal());
	        System.out.println("Discount: RS" + getDiscount());
	        System.out.println("Final Total: RS" + (getTotal() - getDiscount()));
	        System.out.println("Payment Mode: " + getPaymentMode());
            System.out.println("Delivery Partner: " + getDelivaryPartner().getName());
            System.out.println("-----------------");
	    }
	
}
