package com.example.solodolphin.models;

public class HomeVerModel {
    int id;
    byte[] image;
    String name;
    String cate;
    String des;
    int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HomeVerModel(byte[] image, String name, String cate, String des, int price) {
        this.image = image;
        this.name = name;
        this.cate = cate;
        this.des = des;
        this.price = price;
    }

    public HomeVerModel(int id, byte[] image, String name, String cate, String des, int price) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.cate = cate;
        this.des = des;
        this.price = price;
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

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
