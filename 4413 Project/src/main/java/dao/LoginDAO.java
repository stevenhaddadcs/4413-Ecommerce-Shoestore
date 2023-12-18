package dao;

import java.util.List;

import model.Cart;
import model.Shoe;
import model.User;

public interface LoginDAO {
	public boolean isValid(String user, String pass);
	public boolean userAvailable(String user);
	public void register(String user, String pass);
	public boolean isAdmin(String user);
	public void changeUser(String usernameOld, String usernameNew);
    public void changePassword(String username, String password);
    public void changeAddress(String username, String address);
    public void changeCC(String username, String cc);
    public User getUser(String username);
}
