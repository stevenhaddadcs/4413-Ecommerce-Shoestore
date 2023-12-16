import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import model.*;

public class PurchaseDAOImpl implements PurchaseDAO {
    ServletContext sc;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
        }
    }

    public PurchaseDAOImpl(ServletContext servletContext) {
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

    // change this to get all purchases
    public ArrayList<Purchase> getAllPurchases() {
        ArrayList<Purchase> result = new ArrayList<>();

        String sql = "SELECT * FROM PURCHASES";

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int purchaseId = resultSet.getInt("purchase_id");
                String username = resultSet.getString("username");
                String itemsIds = resultSet.getString("items_ids");
                String ccNumber = resultSet.getString("cc_number");
                String address = resultSet.getString("address");
                String purchaseDate = resultSet.getString("purchase_date");

                Purchase purchase = new Purchase(purchaseId, username, itemsIds, ccNumber, address, purchaseDate);
                result.add(purchase);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }

        return result;
    }

    // get purchases by username
    public ArrayList<Purchase> getUserPurchases(String username) {
        ArrayList<Purchase> result = new ArrayList<>();

        String sql = "SELECT * FROM PURCHASES WHERE username = ?";

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int purchaseId = resultSet.getInt("purchase_id");
                // already have username
                String itemsIds = resultSet.getString("items_ids");
                String ccNumber = resultSet.getString("cc_number");
                String address = resultSet.getString("address");
                String purchaseDate = resultSet.getString("purchase_date");

                Purchase purchase = new Purchase(purchaseId, username, itemsIds, ccNumber, address, purchaseDate);
                result.add(purchase);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }

        return result;
    }
}