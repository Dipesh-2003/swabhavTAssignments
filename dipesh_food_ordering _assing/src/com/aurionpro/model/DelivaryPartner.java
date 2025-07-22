package com.aurionpro.model;

public class DelivaryPartner {
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public DelivaryPartner(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	private String name;
}	
