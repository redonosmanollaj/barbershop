package com.example.barbershop.models;

public class Location {
    private String barbershop;
    private String street;
    private String building;
    private String city;
    private String country;

    public Location(){}

    public Location(String barbershop, String street, String building, String city, String country) {
        this.barbershop = barbershop;
        this.street = street;
        this.building = building;
        this.city = city;
        this.country = country;
    }

    public String getBarbershop() {
        return barbershop;
    }

    public void setBarbershop(String barbershop) {
        this.barbershop = barbershop;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
