package dao;

import java.util.List;

import model.Shoe;


public interface ShoeDAO {

	public List<Shoe> findAllShoes();
	
	public List<Shoe> searchShoesByModel(String m);
	
	public List<Shoe> searchShoesByKeyword(String keyWord);
	
	public List<String> findAllBrands();
	
	public List<Shoe> findShoesInSize(String size);

	public void insert(Shoe shoe);

	public void delete(int id);
	
	public List<Shoe> findShoesByBrand(String brand);
	
	public int getShoeStocks(String stockId);

}
