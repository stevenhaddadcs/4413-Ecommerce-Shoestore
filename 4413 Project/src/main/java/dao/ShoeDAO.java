package dao;

import java.util.List;

import model.Shoe;

public interface ShoeDAO {

	public List<Shoe> findAllShoes();

	public List<Shoe> searchShoesByModel(String m, String c);

	public List<Shoe> searchShoesByKeyword(String keyWord);

	public List<String> findAllBrands();

	public List<Shoe> findShoesInSize(String size);

	public void insert(Shoe shoe);

	// USE ADMIN DAO DELETE SHOE INSTEAD
	// public void delete(int id);

	public List<Shoe> findShoesByBrand(String brand);

	public int getShoeStocks(String stockId);

	public Shoe searchShoesByMCS(String m, String c, String s);

}
