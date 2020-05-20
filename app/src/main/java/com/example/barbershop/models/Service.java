package com.example.barbershop.models;

public class Service {
    private int id;
    private String name;
    private String price;
    private int duration;
    private String description;

    public Service(){}

    public Service(int id, String name, String price, int duration, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getDuration() {
        return duration;
    }

    public String getDurationMinutes() {
        return this.duration+" minutes";
    }

    public String getPriceCurrency(){
        return this.price + " $";
    }

    public String getDescription() {
        if(description == "null")
            description = " ";
        return description;
    }
}
