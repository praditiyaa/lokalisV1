package com.lokalis.project;

public class    ItemHelperClass {

    String itemName, itemPrice, itemImage;

    public ItemHelperClass() {
    }

    public ItemHelperClass(String itemName, String itemPrice, String itemImage) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemImage = itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemImages() {
        return itemImage;
    }

    public void setItemImages(String itemImages) {
        this.itemImage = itemImages;
    }
}
