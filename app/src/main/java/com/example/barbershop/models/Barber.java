package com.example.barbershop.models;

import org.json.JSONObject;

public class Barber {
    private int id;
    private int userId;
    private String name;
    private String phone;
    private String city;
    private String status;
    private String building;
    private String barbershop;
    private String street;

    public String getTime() {
        return time;
    }

    private String time;

    public String getStatus() {
        return status;
    }

    public Barber(String name, String barbershop, String street, String building, String city, String status) {
        this.name = name;
        this.city = city;
        this.building = building;
        this.barbershop = barbershop;
        this.street = street;
        this.status = status;
    }




    public String getBarbershop() {
        return barbershop;
    }

    public String getStreet() {
        return street;
    }

    public String getBuilding() {
        return building;
    }

    public String getCountry() {
        return country;
    }

    private String country;
    private double reviews;

    public Barber(String name, String phone, int id, int userId, String barbershop,
                  String street, String building, String city,
                  String country) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.city = city;
        this.barbershop = barbershop;
        this.street = street;
        this.building = building;
        this.country = country;
        this.userId = userId;
    }



    public Barber(int id, String name, String phone, String city) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.city = city;
    }

    public Barber(String name, String city)
    {
        this.name = name;
        this.city = city;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
