package model;

import java.util.ArrayList;

public class Cart {
	private ArrayList<Product> items; //list of item in cart
	
	public Cart() {
		items = new ArrayList<>();
	}
	
	//add a new product
	public void add(Product ci) {
		for(Product x : items) {
			if(ci.getId() == x.getId()) {
				x.setNumber(x.getNumber() + 1);
				return;
			}
		}
		items.add(ci);
	}
	
	//remove a product
	public void remove(int id) {
		for(Product x : items) {
			if(x.getId() == id) {
				items.remove(x);
				return;
			}
		}
	}
	
	//total amount of cart
	public double getAmount() {
		double s = 0;
		for (Product x : items) {
			s += x.getPrice() * x.getNumber();
		}
		return Math.round(s * 100.0) / 100.0;
	}
	
	//list of products in cart
	public ArrayList<Product> getItems() {
		return items;
	}
	
	public int getNumberOfProduct() {
		int number = 0;
		for(Product x : items) {
			number += x.getNumber();
		}
		return number;
	}
}
