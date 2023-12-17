package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.*;

import javax.servlet.ServletContext;

public class CartDAOImpl implements CartDAO {
    ServletContext sc;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
        }
    }

    public CartDAOImpl(ServletContext servletContext) {
        sc = servletContext;
    }

    private Connection getConnection() throws SQLException {
        String path = sc.getRealPath("/dbFile/Shoestore.db");
        return DriverManager.getConnection("jdbc:sqlite:" + path);

    }

    private void closeConnection(Connection connection) {
        if (connection == null)
            return;
        try {
            connection.close();
        } catch (SQLException ex) {
        }
    }

    @Override
    public void checkout(Cart cart, String username, String cc_number, String address) {
        String stockUpdateSQL = "";
        
        Connection connection = null;
        try {
            connection = getConnection();

            String item_ids = "";
            
            PreparedStatement stockUpdateStatement;
            //connection.setAutoCommit(false);
            for (Shoe shoe : cart.getAll()) {
            	stockUpdateSQL = "UPDATE shoestock SET stock = stock - ? WHERE stock_id = ?";
              	stockUpdateStatement = connection.prepareStatement(stockUpdateSQL);
              	stockUpdateStatement.setInt(1, 1);
              	String stock_id = "";
              	float temp = Float.valueOf(shoe.getSize());
        		if(temp == Math.floor(temp)) {
        			int tempInt = Math.round(temp);
        			stock_id = shoe.getId() + "_" + tempInt;
        		}else {
        			stock_id = shoe.getId() + "_" + temp;
        		}
              	item_ids += stock_id + ", ";
                stockUpdateStatement.setString(2, stock_id);
                stockUpdateStatement.executeUpdate();
            }
            
            // remove last comma and space
            item_ids = item_ids.substring(0, item_ids.length() - 2);
            // insert into purchases table (the purchase id will be auto generated)
            String purchaseString = "INSERT INTO PURCHASES (username, items_ids, cc_number, address, purchase_date) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement purchaseStatement = connection.prepareStatement(purchaseString);
            purchaseStatement.setString(1, username);
            purchaseStatement.setString(2, item_ids);
            purchaseStatement.setString(3, cc_number);
            purchaseStatement.setString(4, address);
            purchaseStatement.setString(5, java.time.LocalDate.now().toString());
            purchaseStatement.executeUpdate();

            PreparedStatement test = connection.prepareStatement("SELECT * FROM PURCHASES");
            ResultSet rs = test.executeQuery();
            
            while(rs.next()) {
            	System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        
    }

    // get
}