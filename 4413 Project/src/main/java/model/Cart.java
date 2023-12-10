package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	private List<Shoe> cart;
	
	public Cart() {
		cart = new ArrayList<Shoe>();
	}
	
	public void add(int id, String model, String colourway, String brand, float price, float size, String imageString, int stock) {
		Shoe shoe = new Shoe();
		shoe.setId(id);
		shoe.setModel(model);
		shoe.setColourway(colourway);
		shoe.setBrand(brand);
		shoe.setPrice(price);
		shoe.setSize(size);
		shoe.setImageString(imageString);
		shoe.setStock(stock);
		
		cart.add(shoe);
	}
	public void remove(int id) {
		
	}
}
