package com.example.solodolphin.sqlite;

public class User {
    int userId;
    String userName;
    String userMail;
    String userPass;
    String userAddress;
    String isAdmin;

    public User() {
    }



    public User(int userId, String userName, String userMail, String userPass, String userAddress) {
        this.userId = userId;
        this.userName = userName;
        this.userMail = userMail;
        this.userPass = userPass;
        this.userAddress = userAddress;
    }

    public User(String userName, String userMail, String userPass, String userAddress, String isAdmin) {
        this.userName = userName;
        this.userMail = userMail;
        this.userPass = userPass;
        this.userAddress = userAddress;
        this.isAdmin = isAdmin;
    }

    public User(int userId, String userName, String userMail, String userPass, String userAddress, String isAdmin) {
        this.userId = userId;
        this.userName = userName;
        this.userMail = userMail;
        this.userPass = userPass;
        this.userAddress = userAddress;
        this.isAdmin = isAdmin;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userMail='" + userMail + '\'' +
                ", userPass='" + userPass + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", isAdmin='" + isAdmin + '\'' +
                '}';
    }
}
