package com.example.solodolphin.models;

public class DetailedDailyModel {
    int id;
    byte[] image;
    String name;
    String description;
    String cate;
    int price;


    public DetailedDailyModel(byte[] image, String name, String description, String cate, int price) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.cate = cate;
        this.price = price;
    }

    public DetailedDailyModel(int id, byte[] image, String name, String description, String cate, int price) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.description = description;
        this.cate = cate;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return cate;
    }

    public void setRating(String rating) {
        this.cate = rating;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }



}
