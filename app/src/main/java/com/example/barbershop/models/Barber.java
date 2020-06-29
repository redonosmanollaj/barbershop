package com.example.barbershop.models;

public class Barber {
    private int id;
    private int userId;
    private String name;
    private String phone;
    private String city;

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

    private String barbershop;
    private String street;
    private String building;
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
