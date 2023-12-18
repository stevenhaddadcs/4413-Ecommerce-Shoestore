package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import model.Shoe;

public class ShoeDAOImpl implements ShoeDAO {
	ServletContext sc;

	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException ex) {
		}
	}

	public ShoeDAOImpl(ServletContext servletContext) {
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

	public List<Shoe> findAllShoes() {
		List<Shoe> result = new ArrayList<Shoe>();

		String sql = "select * from shoetypes";

		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {

				Shoe shoe = new Shoe();

				int id = resultSet.getInt("shoe_id");
				String model = resultSet.getString("model");
				String colourway = resultSet.getString("colourway");
				String brand = resultSet.getString("brand");
				float price = resultSet.getFloat("price");
				String imageString = resultSet.getString("image_name");

				shoe.setId(id);
				shoe.setModel(model);
				shoe.setColourway(colourway);
				shoe.setBrand(brand);
				shoe.setPrice(price);
				shoe.setImageString(imageString);
				result.add(shoe);

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}

	@Override
	public List<Shoe> searchShoesByModel(String m, String c) {
		List<Shoe> result = new ArrayList<Shoe>();

		String sql = "select * from shoetypes join shoestock where model = '" + m + "'and colourway = '" + c
				+ "' and shoetypes.shoe_id = shoestock.shoe_id";

		Connection connection = null;
		try {

			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {

				Shoe shoe = new Shoe();

				int id = resultSet.getInt("shoe_id");
				String model = resultSet.getString("model");
				String colourway = resultSet.getString("colourway");
				String brand = resultSet.getString("brand");
				float price = resultSet.getFloat("price");
				float size = resultSet.getFloat("shoe_size");
				String imageString = resultSet.getString("image_name");

				shoe.setId(id);
				shoe.setModel(model);
				shoe.setColourway(colourway);
				shoe.setBrand(brand);
				shoe.setPrice(price);
				shoe.setSize(size);
				shoe.setImageString(imageString);
				result.add(shoe);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}

		return result;
	}

	// complete this method
	public List<String> findAllBrands() {
		List<String> result = new ArrayList<>();
		String sql = "select distinct brand from shoetypes";

		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {

				String brand = resultSet.getString("brand");

				result.add(brand);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}

	public List<Shoe> findShoesByBrand(String brandName) {
		List<Shoe> result = new ArrayList<Shoe>();

		String sql = "select * from shoetypes where brand = '" + brandName + "'";
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {

				Shoe shoe = new Shoe();

				int id = resultSet.getInt("shoe_id");
				String model = resultSet.getString("model");
				String colourway = resultSet.getString("colourway");
				String brand = resultSet.getString("brand");
				float price = resultSet.getFloat("price");
				String imageString = resultSet.getString("image_name");

				shoe.setId(id);
				shoe.setModel(model);
				shoe.setColourway(colourway);
				shoe.setBrand(brand);
				shoe.setPrice(price);
				shoe.setImageString(imageString);
				result.add(shoe);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}

	public List<Shoe> findShoesInSize(String size) {
		List<Shoe> result = new ArrayList<Shoe>();

		// find only models that are available in a certain size
		String sql = "SELECT SHOESTOCK.stock_id, SHOESTOCK.shoe_id, SHOESTOCK.shoe_size, SHOESTOCK.stock,\r\n"
				+ "       SHOETYPES.MODEL, SHOETYPES.COLOURWAY, SHOETYPES.BRAND, SHOETYPES.PRICE, SHOETYPES.IMAGE_NAME\r\n"
				+ "FROM SHOESTOCK\r\n"
				+ "INNER JOIN SHOETYPES ON SHOESTOCK.shoe_id = SHOETYPES.shoe_id\r\n"
				+ "WHERE SHOESTOCK.shoe_size = ? AND SHOESTOCK.stock > 0;\r\n";
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, size);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {

				Shoe shoe = new Shoe();

				int id = resultSet.getInt("shoe_id");
				String model = resultSet.getString("model");
				String colourway = resultSet.getString("colourway");
				String brand = resultSet.getString("brand");
				float price = resultSet.getFloat("price");
				String imageString = resultSet.getString("image_name");
				int stock = resultSet.getInt("stock");

				shoe.setId(id);
				shoe.setModel(model);
				shoe.setColourway(colourway);
				shoe.setBrand(brand);
				shoe.setPrice(price);
				shoe.setImageString(imageString);
				shoe.setStock(stock);
				result.add(shoe);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;

	}

	public void insert(Shoe shoe) {
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"insert into Shoe (Model) values (?)",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, shoe.getModel());
			statement.execute();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				shoe.setId(generatedKeys.getInt(1));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}

	// USE ADMIN DAO DELETE SHOE INSTEAD
	// public void delete(int id) {
	// Connection connection = null;

	// try {
	// connection = getConnection();
	// PreparedStatement statement = connection
	// .prepareStatement("delete from shoetypes where shoe_id=?");
	// statement.setInt(1, id);
	// statement.execute();
	// } catch (SQLException ex) {
	// ex.printStackTrace();
	// } finally {
	// closeConnection(connection);
	// }
	// }

	public int getShoeStocks(String stockId) {
		int stock = 0;
		String sql = "select stock from shoestock where stock_id = '" + stockId + "'";
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				stock = resultSet.getInt("stock");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return stock;
	}

	public List<Shoe> searchShoesByKeyword(String keyWord) {
		List<Shoe> result = new ArrayList<Shoe>();

		String sql = "select * from shoetypes "
				+ " where model like '%"
				+ keyWord.trim()
				+ "%'"
				+ " or brand like '%"
				+ keyWord.trim()
				+ "%'"
				+ " or colourway like '%" + keyWord.trim() + "%'";

		Connection connection = null;
		try {

			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Shoe shoe = new Shoe();

				int id = resultSet.getInt("shoe_id");
				String model = resultSet.getString("model");
				String colourway = resultSet.getString("colourway");
				String brand = resultSet.getString("brand");
				float price = resultSet.getFloat("price");
				String imageString = resultSet.getString("image_name");
				// int stock = resultSet.getInt("stock");

				shoe.setId(id);
				shoe.setModel(model);
				shoe.setColourway(colourway);
				shoe.setBrand(brand);
				shoe.setPrice(price);
				shoe.setImageString(imageString);
				// show stock in the display
				// shoe.setStock(stock);
				result.add(shoe);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}

		return result;
	}

	public Shoe searchShoesByMCS(String m, String c, String s) {

		Shoe result = new Shoe();
		String sql = "select * from shoetypes join shoestock where model = '" + m + "'and colourway = '" + c
				+ "' and shoetypes.shoe_id = shoestock.shoe_id and shoestock.shoe_size ='" + s + "'";

		Connection connection = null;
		try {

			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {

				Shoe shoe = new Shoe();

				int id = resultSet.getInt("shoe_id");
				String model = resultSet.getString("model");
				String colourway = resultSet.getString("colourway");
				String brand = resultSet.getString("brand");
				float price = resultSet.getFloat("price");
				float size = resultSet.getFloat("shoe_size");
				String imageString = resultSet.getString("image_name");

				shoe.setId(id);
				shoe.setModel(model);
				shoe.setColourway(colourway);
				shoe.setBrand(brand);
				shoe.setPrice(price);
				shoe.setSize(size);
				shoe.setImageString(imageString);
				return shoe;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}

		return result;
	}

}