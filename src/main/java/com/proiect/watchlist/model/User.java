package com.proiect.watchlist.model;

public class User {

    private Integer id;
    private String user_name;
    private String password;

    public User(){

    }

    public User(Integer id, String user_name, String password) {
        this.id = id;
        this.user_name = user_name;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", user_Name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
