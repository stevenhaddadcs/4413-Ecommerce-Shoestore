package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	private List<Shoe> cart;

	public Cart() {
		cart = new ArrayList<Shoe>();
	}

	public void add(Shoe shoe) {
		shoe.incrementOrderQty(1);
		cart.add(shoe);
	}

	public void add(Shoe shoe, int qty) {
		shoe.setOrderQty(qty);
		cart.add(shoe);
	}

	public void remove(int id) {
		for (int i = 0; i < cart.size();) {
			if (cart.get(i).getId() == id) {
				cart.remove(i);
			}
		}

	}

	public List<Shoe> getAll() {
		return cart;
	}

	public void setAll(List<Shoe> shoes) {
		cart = shoes;
	}

}
