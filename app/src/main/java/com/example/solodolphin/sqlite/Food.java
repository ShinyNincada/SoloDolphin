package com.example.solodolphin.sqlite;

import java.io.Serializable;

public class Food implements Serializable {
    int id;
    String name;
    String cate;
    int price;
    String Des;
    String Dis;
    byte[] imgID;

    public Food(String name, String cate, int price, byte[] imgID) {
        this.name = name;
        this.cate = cate;
        this.price = price;
        this.imgID = imgID;
    }

    public Food(String name, String cate, int price, String des, String dis, byte[] imgID) {

        this.name = name;
        this.cate = cate;
        this.price = price;
        Des = des;
        Dis = dis;
        this.imgID = imgID;
    }

    public Food(int id, String name, String cate, int price, String des, String dis, byte[] imgID) {
        this.id = id;
        this.name = name;
        this.cate = cate;
        this.price = price;
        Des = des;
        Dis = dis;
        this.imgID = imgID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDes() {
        return Des;
    }

    public void setDes(String des) {
        Des = des;
    }

    public String getDis() {
        return Dis;
    }

    public void setDis(String dis) {
        Dis = dis;
    }

    public byte[] getImgID() {
        return imgID;
    }

    public void setImgID(byte[] imgID) {
        this.imgID = imgID;
    }
}
