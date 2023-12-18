package dao;

import java.util.List;

import model.Cart;
import model.Shoe;

public interface LoginDAO {
	public boolean isValid(String user, String pass);
	public boolean userAvailable(String user);
	public void register(String user, String pass);
}
