package dao;

import java.util.List;

import model.Cart;
import model.Shoe;

public interface CartDAO {

	public void checkout(Cart cart, String username, String cc_number, String address);
}
