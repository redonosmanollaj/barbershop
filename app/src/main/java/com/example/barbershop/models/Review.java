package com.example.barbershop.models;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Review {
    private int id;
    private String clientName;
    private int stars;
    private String content;
    private String createdAt;

    public Review(){}

    public Review(int id, String clientName, int stars, String content, String createdAt) {
        this.id = id;
        this.clientName = clientName;
        this.stars = stars;
        this.content = content;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public String getClientName() {
        return clientName;
    }

    public int getStars() {
        return stars;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getElapsedTime(){
        String elapsedTime = "";
        try {
            DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            DateFormat outputFormat = new SimpleDateFormat("EEEE, dd/MM/yyyy/hh:mm:ss");
            Date date = inputFormat.parse(this.createdAt);
            PrettyTime prettyTime = new PrettyTime();
            elapsedTime = prettyTime.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return elapsedTime;
    }
}
