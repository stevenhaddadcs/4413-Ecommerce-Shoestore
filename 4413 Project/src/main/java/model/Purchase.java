package model;

public class Purchase {
    private int purchase_id;
    private String username;
    private String items_ids;
    private String cc_number;
    private String address;
    private String purchase_date;

    // Constructors, getters, and setters

    public Purchase(int purchase_id, String username, String items_ids, String cc_number, String address,
            String purchase_date) {
        this.purchase_id = purchase_id;
        this.username = username;
        this.items_ids = items_ids;
        this.cc_number = cc_number;
        this.address = address;
        this.purchase_date = purchase_date;
    }

    // Getters and setters

    public int getPurchaseId() {
        return purchase_id;
    }

    public void setPurchaseId(int purchase_id) {
        this.purchase_id = purchase_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getItemsIds() {
        return items_ids;
    }

    public void setItemsIds(String items_ids) {
        this.items_ids = items_ids;
    }

    public String getCcNumber() {
        return cc_number;
    }

    public void setCcNumber(String cc_number) {
        this.cc_number = cc_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPurchaseDate() {
        return purchase_date;
    }

    public void setPurchaseDate(String purchase_date) {
        this.purchase_date = purchase_date;
    }
}
