package com.nickeecoco.myyelp;

public class Favorite {
    public String name;
    public String imageURL;
    public String categories;
    public double rating;
    public String price;
    public String phone;
    public String address;

    public Favorite(String name, String imageURL, String categories, double rating, String price, String phone, String address) {
        this.name = name;
        this.imageURL = imageURL;
        this.categories = categories;
        this.rating = rating;
        this.price = price;
        this.phone = phone;
        this.address = address;
    }
}
