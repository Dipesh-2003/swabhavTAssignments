package com.aurionpro.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Menu implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8198385464251665088L;
	private List<FoodItem> items = new ArrayList<>();

	public void addItems(FoodItem item) {
		items.add(item);
	}

	public List<FoodItem> getItem() {
		return items;
	}

	public void displayMenu() {
	    for (FoodItem item : items) {
	        System.out.println("🍴-> " + item.getId() + " ➡️ " + item.getName() + " ➡️ RS: " + item.getPrice());
	    }
	}

	public boolean removeItem(int id) {
		Optional<FoodItem> itemToRemove = items.stream().filter(item -> item.getId() == id).findFirst();

		if (itemToRemove.isPresent()) {
			items.remove(itemToRemove.get());
			return true;
		}
		return false;
	}
}
