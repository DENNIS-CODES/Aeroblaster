package com.example.hamzafood;

public class FoodDetails {

    public String Dishes,Quantity,Price,Description,ImageURL,RandomUID,Chefid;
    // Alt+insert
//food data
    public FoodDetails(String dishes, String quantity, String price, String description, String imageURL, String randomUID, String chefid) {
        Dishes = dishes;
        Quantity = quantity;
        Price = price;
        Description = description;
        ImageURL = imageURL;
        RandomUID = randomUID;
        Chefid = chefid;
    }
}