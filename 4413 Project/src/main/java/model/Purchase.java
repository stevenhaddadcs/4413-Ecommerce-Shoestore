package model;

import java.util.Date;

public class Purchase {
    private int purchaseId;
    private String username;
    private String itemsIds;
    private String ccNumber;
    private String address;
    private String purchaseDate;

    // Constructors, getters, and setters

    public Purchase(int purchaseId, String username, String itemsIds, String ccNumber, String address,
            String purchaseDate) {
        this.purchaseId = purchaseId;
        this.username = username;
        this.itemsIds = itemsIds;
        this.ccNumber = ccNumber;
        this.address = address;
        this.purchaseDate = purchaseDate;
    }

    // Getters and setters

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getItemsIds() {
        return itemsIds;
    }

    public void setItemsIds(String itemsIds) {
        this.itemsIds = itemsIds;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
