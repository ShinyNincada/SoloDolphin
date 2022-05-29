package com.example.solodolphin.models;

public class CartModel {
    int id;
    byte[] image;
    String name;
    String desc;
    String cate;
    int quantity;
    int price;




    public CartModel(byte[] image, String name, String cate, int price) {
        this.image = image;
        this.name = name;
        this.cate = cate;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CartModel(int id, byte[] image, String name, String cate, int price) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.cate = cate;
        this.price = price;
    }

    public CartModel(int id, byte[] image, String name, String cate, int price,  int quantity) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.cate = cate;
        this.price = price;
        this.quantity = quantity;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
