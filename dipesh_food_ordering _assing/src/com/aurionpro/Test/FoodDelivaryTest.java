package com.aurionpro.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import com.aurionpro.model.Customer;
import com.aurionpro.model.DelivaryPartner;
import com.aurionpro.model.FoodItem;
import com.aurionpro.model.Menu;
import com.aurionpro.model.Order;
import com.aurionpro.model.PaymentProcess;

public class FoodDelivaryTest {
	private static Menu menu = new Menu();
	private static DelivaryPartner[] partners = { new DelivaryPartner(1, "Dipesh"), new DelivaryPartner(2, "Adarsh") };
	private static Scanner sc = new Scanner(System.in);
	private static boolean running = true;
	private static PaymentProcess paymentProcess = new PaymentProcess();

	public static void main(String[] args) {
		System.out.println("Welcome to Dipesh Food Delivary App!!");
		while (running) {
			System.out.println(" 1.Admin💻 \n 2.Customer🛒 \n 3.Exit ⬅️");
			System.out.println("Select Option:");
			int choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1: {
				System.out.println("---Admin Menu---");
				System.out.println("Enter admin username: ");
				String adminUsername = sc.nextLine();
				if (adminUsername.equalsIgnoreCase("admin123")) {
					System.out.println("Access granted to admin pannel ✅");
					adminMenu();

				} else {
					System.out.println("Invalid user name, Access Denied ❌");
				}
				break;
			}

			case 2:
				customerMenu();
				break;
				
			case 3:
				System.out.println("closing app...");
				running =false;
				break;
			default:
				System.out.println("Invalid Choice ❌");
			}
		}
	}

	public static void adminMenu() {
		boolean adminRunning = true;
		while (adminRunning) {
			System.out.println("\n1. Add Items \n2. View Menu \n3. Remove Item \n4. Back");
			int option = sc.nextInt();
			switch (option) {
			case 1: {
				loadMenu();
				sc.nextLine();
				System.out.println("Enter Item Name: "); 
				String name = sc.nextLine().trim();
				System.out.println("Enter Item Price: ");
				int price = sc.nextInt();
				int id = menu.getItem().size() + 1;
				menu.addItems(new FoodItem(id, name, price));
				saveMenu();
				System.out.println("Items added sucessfully" + " ID: " + id + " Name: " + name + " Price: " + price);
				break;
			}

			case 2: {
				loadMenu();

				break;
			}
			case 3:
				 loadMenu();
//	                menu.displayMenu();

	                if (menu.getItem().isEmpty()) {
	                    System.out.println("Menu is empty, nothing to remove.");
	                    break;
	                }

	                System.out.println("Enter the ID of the item to remove:");
	                int idToRemove = sc.nextInt();
	                sc.nextLine();

	                if (menu.removeItem(idToRemove)) {
	                    System.out.println("Item removed successfully.");
	                    saveMenu(); 
	                } else {
	                    System.out.println("Item not found with that ID.");
	                }
	                break;
	            

			case 4:
				adminRunning = false;
				break;

			default:
				System.out.println("Invaid choice");
			}
		}
	}

	public static void loadMenu() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Menu.txt"));
			menu = (Menu) ois.readObject();
			ois.close();
			System.out.println("---Menu📒---");
			menu.displayMenu();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("No existing menu found.");
			menu = new Menu();
		}
	}

	public static void saveMenu() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Menu.txt"));
			oos.writeObject(menu);
			oos.close();
			System.out.println("Menu saved successfully.");
		} catch (IOException e) {
			System.out.println("Error saving menu: " + e.getMessage());
		}

	}

	public static boolean manageCart(Order order) {
		boolean cartOpen = true;
		while (cartOpen) {
			order.recalculateTotal();
			System.out.println("\n---- Your Current Order ----");
			if (order.getItems().isEmpty()) {
				System.out.println("Your cart is empty.");
				return false; 
			}

			for (Map.Entry<FoodItem, Integer> entry : order.getItems().entrySet()) {
				System.out.println(
						"ID: " + entry.getKey().getId() + " | " + entry.getKey().getName() + " x " + entry.getValue());
			}
			System.out.println("----------------------------");
			System.out.println("Total: RS" + order.getTotal());

			System.out.println("\n1. Proceed to Payment");
			System.out.println("2. Remove an item from order");
			System.out.println("3. Go back and add more items");
			int cartChoice = sc.nextInt();
			sc.nextLine();

			switch (cartChoice) {
			case 1: 
				
				order.recalculateTotal();
				double total = order.getTotal();
				double discount = (total > 500) ? total * 0.1 : 0; 
				order.setDiscount(discount);
				order.setTotal(total);

				System.out.println("Select Payment Method: \n1. UPI \n2. Cash");
				int paymentMode = sc.nextInt();
				sc.nextLine(); 
				if (paymentMode == 1) {
					order.setPaymentMode("UPI");
				} else {
					order.setPaymentMode("Cash");
				}
				Random rand = new Random();
				order.setDelivaryPartner(partners[rand.nextInt(partners.length)]);
				paymentProcess.paymentProcess(order.getPaymentMode(), order.getTotal() - order.getDiscount());
				order.invoice(order);
				return true; 
			
			case 2: 
				System.out.println("Enter the ID of the item to remove:");
				int idToRemove = sc.nextInt();
				sc.nextLine(); 
				FoodItem itemToRemove = order.getItems().keySet().stream().filter(key -> key.getId() == idToRemove)
						.findFirst().orElse(null);
				if (itemToRemove != null) {
					order.removeItemFromOrder(itemToRemove);
				} else {
					System.out.println("Invalid ID. Item not in your cart.");
				}
				break;

			case 3: 
				return false; 
				

			default:
				System.out.println("Invalid Choice. Please try again.");
			}
		}
		return false;
	}

	public static void customerMenu() {
		boolean customerRunning = true;
		System.out.println("Enter you name: ");
		String name = sc.next();
		System.out.println("Welcome " + name + " ❣️");
		Customer customer = new Customer(name);
		Order order = new Order();
		
		while (customerRunning) {
			System.out.println("\n1. View Menu & Add Item \n2. View Cart & Finalize Order \n3. Exit");
			int choice = sc.nextInt();
			switch (choice) {
			case 1: {
				loadMenu();
//				menu.displayMenu();
				System.out.println("Enter ID to add (or 0 to go back):");
				int id = sc.nextInt();
				if (id == 0)
					continue;
				FoodItem item = menu.getItem().stream().filter(f -> f.getId() == id).findFirst().orElse(null);

				if (item != null) {
					System.out.println("Enter the Quantity:");
					int qty = sc.nextInt();
					sc.nextLine();
					order.addItems(item, qty);
					System.out.println(item.getName() + " added to your order.");
				} else {
					System.out.println("Invalid item ID.");
				}
				break;

			}
			case 2:
				if (order.getItems().isEmpty()) {
                    System.out.println("Your cart is empty. Please add items first.");
                    continue; 
                }
			    boolean orderIsFinalized = manageCart(order);
			    if (orderIsFinalized) {
                    customerRunning = false;
                }
                break;
                
                case 3:
                	  customerRunning = false;
                      break;
                  
                  default:
                      System.out.println("Invalid Choice ❌");
			}
		}
	}
}
