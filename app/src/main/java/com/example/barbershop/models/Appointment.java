package com.example.barbershop.models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Appointment {
    private int id;
    private String clientName;
    private String barberName;
    private List<Service> services;
    private String time;
    private String status;

    public Appointment(){}

    public Appointment(int id, String clientName, String barberName, List<Service> services, String time, String status) {
        this.id = id;
        this.clientName = clientName;
        this.barberName = barberName;
        this.services = services;
        this.time = time;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getClientName() {
        return clientName;
    }

    public String getBarberName() {
        return barberName;
    }

    public String getServices() {
        String strServices = "";
        for(int i = 0; i<services.size(); i++){
            if(i == 0)
                strServices = services.get(i).getName();
            else
                strServices = strServices + " + " + services.get(i).getName();
        }
        return strServices;
    }

    public String getTime() {
        return time;
    }

    public String getFormatedTime(){
        Date date = null;
        Date formatedDate = null;
        try {
            DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            date = inputFormat.parse(this.time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.toString();
    }

    public String getStatus() {
        return status;
    }
}
