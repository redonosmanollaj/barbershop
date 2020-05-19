package com.example.barbershop.models;

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
        return "";
    }
}
