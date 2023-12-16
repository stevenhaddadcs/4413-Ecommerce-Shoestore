package model;

public class Shoe {

    private int id;
    private String model;
    private String colourway;
    private String brand;
    private float price;
    private float size;
    private String imageString;
    private int stock;
    // private int orderQty;

    public Shoe() {
        // orderQty = 0;
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

// public void incrementOrderQty(int qty) {
// orderQty += qty;
// }

// public void setOrderQty(int orderQty) {
// this.orderQty = orderQty;
// }

// public int getOrderQty() {
// return 0;
// }
// }
