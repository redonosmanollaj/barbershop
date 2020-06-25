package com.example.barbershop.models;

public class FavoriteBarbers
{
    private String name;
    private String email;
    private String updatetAt;
    private String phone;
    private String emailVerifiedAt;
    private String createdAt;

    public FavoriteBarbers(String name) {
        this.name = name;
    }


    public FavoriteBarbers(String name, String email) {
        this.name = name;
        this.email = email;
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

    public String getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatetAt() {
        return updatetAt;
    }

}
