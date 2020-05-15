package com.example.barbershop.models;

public class User {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String role;
    private String avatar;

    public User(){}

    public User(int id, String name, String email, String phone, String role, String avatar) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getRole() {
        return role;
    }

    public String getAvatar() {
        return avatar;
    }
}
