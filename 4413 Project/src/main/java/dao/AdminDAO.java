package dao;

import java.util.ArrayList;
import java.util.List;

import model.*;

public interface AdminDAO {

    public ArrayList<User> getAllUsers();

    public void deleteUser(String username);

    public void deleteShoe(int shoe_id);

    public ArrayList<Shoe> getAllShoeStocks();

    public ArrayList<Shoe> getAllShoeTypes();

    public void updateShoeStock(String stock_id, int stock);

    public void updateShoePrice(int shoe_id, float price);
}