package com.example.barbershop.models;

import java.util.List;

public class Info {
    private Location location;
    private List<Hours> hours;

    public Info(){}

    public Info(Location location, List<Hours> hours) {
        this.location = location;
        this.hours = hours;
    }

    public Location getLocation() {
        return location;
    }

    public List<Hours> getHours() {
        return hours;
    }
}
