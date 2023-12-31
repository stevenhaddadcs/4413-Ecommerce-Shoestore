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

public class AdminDAOImpl implements AdminDAO {
    ServletContext sc;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
        }
    }

    public AdminDAOImpl(ServletContext servletContext) {
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

    // update admin status in database USERS
    @Override
    public void updateAdminStatus(String username, int isAdmin) {
        String sql = "UPDATE USERS SET is_admin = ? WHERE username = ?";

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, isAdmin);
            statement.setString(2, username);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Admin status updated successfully.");
            } else {
                System.out.println("No rows were affected. Admin status update failed.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    // get all users from database USERS
    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM USERS";

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                int isAdmin = resultSet.getInt("is_admin");
                String address = resultSet.getString("address");
                String ccNumber = resultSet.getString("cc_number");

                // Create User object and add it to the list
                User user = new User(username, password, isAdmin, address, ccNumber);
                userList.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return userList;
    }

    // delete user from database based on username
    @Override
    public void deleteUser(String username) {
        String deleteSQL;

        Connection connection = null;
        try {
            connection = getConnection();

            PreparedStatement deleteStatement;
            // connection.setAutoCommit(false);

            deleteSQL = "DELETE FROM USERS WHERE username = ?;";
            deleteStatement = connection.prepareStatement(deleteSQL);
            deleteStatement.setString(1, username);
            deleteStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    // DELETE SHOE TYPE FROM DATABASE and all associated shoe stocks in SHOESTOCK
    @Override
    public void deleteShoe(int shoe_id) {
        Connection connection = null;

        // sql statements to delete shoe type and all associated shoe stocks
        String deleteShoetypesSQL = "DELETE FROM SHOETYPES WHERE shoe_id = ?;";
        String deleteShoestockSQL = "DELETE FROM SHOESTOCK WHERE shoe_id = ?;";

        try {
            connection = getConnection();
            PreparedStatement deleteShoetypesStatement = connection
                    .prepareStatement(deleteShoetypesSQL);
            deleteShoetypesStatement.setInt(1, shoe_id);
            deleteShoetypesStatement.execute();

            PreparedStatement deleteShoestockStatement = connection
                    .prepareStatement(deleteShoestockSQL);
            deleteShoestockStatement.setInt(1, shoe_id);
            deleteShoestockStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    // get all shoe stocks from database SHOESTOCK
    @Override
    public ArrayList<Shoe> getAllShoeStocks() {
        ArrayList<Shoe> result = new ArrayList<Shoe>();

        // find only models that are available in a certain size
        String sql = "SELECT SHOESTOCK.stock_id, SHOESTOCK.shoe_id, SHOESTOCK.shoe_size, SHOESTOCK.stock,\r\n"
                + "       SHOETYPES.MODEL, SHOETYPES.COLOURWAY, SHOETYPES.BRAND, SHOETYPES.PRICE\r\n"
                + "FROM SHOESTOCK\r\n"
                + "INNER JOIN SHOETYPES ON SHOESTOCK.shoe_id = SHOETYPES.shoe_id\r\n";
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                String stock_id = resultSet.getString("stock_id");
                String model = resultSet.getString("MODEL");
                String colourway = resultSet.getString("COLOURWAY");
                String brand = resultSet.getString("BRAND");
                float shoe_size = Float.parseFloat(resultSet.getString("shoe_size"));
                int stock = resultSet.getInt("stock");
                // set all values for the shoe
                Shoe shoe = new Shoe(stock_id, model, colourway, brand, shoe_size, stock);
                result.add(shoe);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return result;

    }

    // get all shoe stocks from database SHOESTOCK
    @Override
    public ArrayList<Shoe> getAllShoeTypes() {
        ArrayList<Shoe> result = new ArrayList<Shoe>();
        String sql = "SELECT SHOE_ID, MODEL, COLOURWAY, BRAND, PRICE from SHOETYPES";

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                int id = resultSet.getInt("SHOE_ID");
                String model = resultSet.getString("MODEL");
                String colourway = resultSet.getString("COLOURWAY");
                String brand = resultSet.getString("BRAND");
                float price = resultSet.getFloat("PRICE");
                // set all values for the shoe
                Shoe shoe = new Shoe(id, model, colourway, brand, price);
                result.add(shoe);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return result;

    }

    // update shoe stock in database SHOESTOCK
    @Override
    public void updateShoeStock(String stock_id, int stock) {

        String sql = "UPDATE SHOESTOCK SET stock = ? WHERE stock_id = ?";

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, stock);
            statement.setString(2, stock_id);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Shoe stock updated successfully.");
            } else {
                System.out.println("No rows were affected. Shoe stock update failed.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    // update shoe price in database SHOETYPES
    public void updateShoePrice(int shoe_id, float price) {
        String sql = "UPDATE SHOETYPES SET price = ? WHERE shoe_id = ?";

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setFloat(1, price);
            statement.setInt(2, shoe_id);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Shoe prices updated successfully.");
            } else {
                System.out.println("No rows were affected. Shoe stock update failed.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    @Override
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

    @Override
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