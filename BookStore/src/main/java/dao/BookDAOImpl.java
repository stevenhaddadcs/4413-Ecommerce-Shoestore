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

import model.Author;
import model.Book;
import model.Category;

public class BookDAOImpl implements BookDAO {
	private ServletContext sc;

	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException ex) {
		}
	}

	//gets the servlet context so that it can establish a connection
	public BookDAOImpl(ServletContext servletContext) {
		sc = servletContext;
	}

	// complete this method
	private Connection getConnection() throws SQLException {
	    // Gets the absolute path to the Books.db file
		String path = sc.getRealPath("/WEB-INF/Books.db") ;
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

	// COMPLETED this method
	public List<Book> findAllBooks() {
		List<Book> result = new ArrayList<Book>();

		// join 3 tables to get needed info
		String sql = "select CATEGORY_ID, BOOK_ID, BOOK_TITLE,PUBLISHER, FIRST_NAME, LAST_NAME, CATEGORY_DESCRIPTION, AUTHOR.ID AS AUTHOR_ID"
				+ " from book inner join author, category on book.id = author.book_id and book.category_id = category.id;";

		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {

				Book book = new Book();
				Author author = new Author();

				// populate book and author beans with needed info, and then set author to book

				author.setBookId(resultSet.getLong("BOOK_ID"));
				author.setFirstName(resultSet.getString("FIRST_NAME"));
				author.setLastName(resultSet.getString("LAST_NAME"));
				author.setId(resultSet.getLong("AUTHOR_ID"));

				book.setAuthor(author);
				book.setBookTitle(resultSet.getString("BOOK_TITLE"));
				book.setCategory(resultSet.getString("CATEGORY_DESCRIPTION"));
				book.setCategoryId(resultSet.getLong("CATEGORY_ID"));
				book.setId(resultSet.getLong("BOOK_ID"));
				result.add(book);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}

	// COMPLETED this method
	@Override
	public List<Book> searchBooksByKeyword(String keyWord) {
		List<Book> result = new ArrayList<Book>();

		String sql = "select CATEGORY_ID, BOOK_ID, BOOK_TITLE,PUBLISHER, FIRST_NAME, LAST_NAME, CATEGORY_DESCRIPTION, AUTHOR.ID AS AUTHOR_ID"
				+ " from book inner join author, category on book.id = author.book_id and book.category_id = category.id"
				+ " where book_title like '%" + keyWord.trim() + "%'" + " or first_name like '%" + keyWord.trim() + "%'"
				+ " or last_name like '%" + keyWord.trim() + "%'";

		Connection connection = null;
		try {

			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Book book = new Book();
				Author author = new Author();

				// populate book and author with needed info, and then set author to book

				author.setBookId(resultSet.getLong("BOOK_ID"));
				author.setFirstName(resultSet.getString("FIRST_NAME"));
				author.setLastName(resultSet.getString("LAST_NAME"));
				author.setId(resultSet.getLong("AUTHOR_ID"));

				book.setAuthor(author);
				book.setBookTitle(resultSet.getString("BOOK_TITLE"));
				book.setCategory(resultSet.getString("CATEGORY_DESCRIPTION"));
				book.setCategoryId(resultSet.getLong("CATEGORY_ID"));
				book.setId(resultSet.getLong("BOOK_ID"));

				result.add(book);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}

		return result;
	}

	// COMPLETED this method
	public List<Category> findAllCategories() {
		List<Category> result = new ArrayList<>();
		String sql = "select * from category";

		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Category category = new Category();

				// populate category bean with needed info
				category.setCategoryDescription(resultSet.getString("CATEGORY_DESCRIPTION"));
				category.setId(resultSet.getLong("ID"));

				result.add(category);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}

	// COMPLETED
	public List<Book> findBooksByCategory(String category) {
		List<Book> result = new ArrayList<Book>();

		String sql = "select CATEGORY_ID, BOOK_ID, BOOK_TITLE,PUBLISHER, FIRST_NAME, LAST_NAME, CATEGORY_DESCRIPTION, AUTHOR.ID AS AUTHOR_ID"
				+ " from book inner join author, category on book.id = author.book_id and book.category_id = category.id  where "
				+ "CATEGORY_DESCRIPTION='" + category + "'";

		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Book book = new Book();
				Author author = new Author();

				// populate book and author beans with needed info, and then set author to book
				author.setBookId(resultSet.getLong("BOOK_ID"));
				author.setFirstName(resultSet.getString("FIRST_NAME"));
				author.setLastName(resultSet.getString("LAST_NAME"));
				author.setId(resultSet.getLong("AUTHOR_ID"));

				book.setAuthor(author);
				book.setBookTitle(resultSet.getString("BOOK_TITLE"));
				book.setCategory(resultSet.getString("CATEGORY_DESCRIPTION"));
				book.setCategoryId(resultSet.getLong("CATEGORY_ID"));
				book.setId(resultSet.getLong("BOOK_ID"));

				result.add(book);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}

	public void insert(Book book) {
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement("insert into Book (book_title) values (?)",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, book.getBookTitle());
			statement.execute();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				book.setId(generatedKeys.getLong(1));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}

	public void delete(Long bookId) {
		Connection connection = null;

		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement("delete from book where id=?");
			statement.setLong(1, bookId);
			statement.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}

}