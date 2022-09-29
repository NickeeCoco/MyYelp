package com.nickeecoco.myyelp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

// mapper
public class YelpResponse {

    @SerializedName("businesses")
    public ArrayList<Business> businesses;

    public static class Business {

        @SerializedName("name")
        public String name;

        @SerializedName("image_url")
        public String imageURL;

        @SerializedName("categories")
        public ArrayList<Category> categories;

        @SerializedName("rating")
        public double rating;

        @SerializedName("price")
        public String price;

        @SerializedName("display_phone")
        public String phone;

        @SerializedName("location")
        public Location location;

    }

    public static class Category {
        @SerializedName("title")
        public String category;
    }

    public static class Location {
        @SerializedName("address1")
        public String address;

        @SerializedName("city")
        public String city;

        @SerializedName("state")
        public String state;
    }

}
