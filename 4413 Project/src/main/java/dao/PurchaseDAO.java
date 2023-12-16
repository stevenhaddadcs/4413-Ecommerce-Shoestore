package dao;

import java.util.ArrayList;

import model.Purchase;

public interface PurchaseDAO {

    public ArrayList<Purchase> getAllPurchases();
    
    // get purchases by username
    public ArrayList<Purchase> getUserPurchases(String username);

}
