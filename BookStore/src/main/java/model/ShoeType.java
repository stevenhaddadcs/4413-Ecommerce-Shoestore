package model;

//used to display each shoetype in the catalog of shoes
public class ShoeType {
	private Long shoeid;
	private String brand;
	private String model;
	private String colourway;
	private float price;
	private String imagename;

	public ShoeType(Long shoeid, String brand, String model, String colourway, float price, String imagename) {
		this.shoeid = shoeid;
		this.brand = brand;
		this.model = model;
		this.colourway = colourway;
		this.price = price;
		this.imagename = imagename;
	}

	public Long getShoeid() {
		return shoeid;
	}

	public void setShoeid(Long shoeid) {
		this.shoeid = shoeid;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColourway() {
		return colourway;
	}

	public void setColourway(String colourway) {
		this.colourway = colourway;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getImageName() {
		return imagename;
	}

	public void setImageName(String imagename) {
		this.imagename = imagename;
	}

}
