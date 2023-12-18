package model;

public class Shoe {

    private String stock_id;
    private int id;
    private String model;
    private String colourway;
    private String brand;
    private float price;
    private float size;
    private String imageString;
    private int stock;
    private int orderQty;

    public Shoe() {
        orderQty = 0;
    }

    // used by admindaoimpl to get all shoe stocks
    public Shoe(int id, String model, String colourway, String brand, float price, float shoe_size, int stock) {
        this.id = id;
        this.model = model;
        this.colourway = colourway;
        this.brand = brand;
        this.price = price;
        this.size = shoe_size;
        this.stock = stock;
        // orderQty = 0;
    }

    // for shoetypes in adminDAO
    public Shoe(int id, String model, String colourway, String brand, float price) {
        this.id = id;
        this.model = model;
        this.colourway = colourway;
        this.brand = brand;
        this.price = price;
    }

    // for shoestock in adminDAO
    public Shoe(String stock_id, String model, String colourway, String brand, float shoe_size, int stock) {
        this.stock_id = stock_id;
        this.model = model;
        this.colourway = colourway;
        this.brand = brand;
        this.size = shoe_size;
        this.stock = stock;
    }

    public String getStock_id() {
        return stock_id;
    }

    public void setStock_id(String stock_id) {
        this.stock_id = stock_id;
    }

    public int getShoe_id() {
        return getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void incrementOrderQty(int qty) {
        orderQty += qty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public int getOrderQty() {
        return orderQty;
    }
}
