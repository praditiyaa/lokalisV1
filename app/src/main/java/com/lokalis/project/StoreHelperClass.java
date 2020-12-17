package com.lokalis.project;

public class StoreHelperClass {

    public String storeName, images, distance;
    public String rating;

    public StoreHelperClass() {
    }

    public StoreHelperClass(String storeName, String images, String distance, String rating) {
        this.storeName = storeName;
        this.images = images;
        this.distance = distance;
        this.rating = rating;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
