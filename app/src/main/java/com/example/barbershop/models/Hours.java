package com.example.barbershop.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Hours {
    private int id;
    private String day;
    private String workStart;
    private String workEnd;
    private String breakStart;
    private String breakEnd;

    public Hours(int id, String day, String workStart, String workEnd, String breakStart, String breakEnd) {
        this.id = id;
        this.day = day;
        this.workStart = workStart;
        this.workEnd = workEnd;
        this.breakStart = breakStart;
        this.breakEnd = breakEnd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getWorkStart() {
        return workStart;
    }

    public void setWorkStart(String workStart) {
        this.workStart = workStart;
    }

    public String getWorkEnd() {
        return workEnd;
    }

    public void setWorkEnd(String workEnd) {
        this.workEnd = workEnd;
    }

    public String getBreakStart() {
        return breakStart;
    }

    public void setBreakStart(String breakStart) {
        this.breakStart = breakStart;
    }

    public String getBreakEnd() {
        return breakEnd;
    }

    public void setBreakEnd(String breakEnd) {
        this.breakEnd = breakEnd;
    }

    public String getWorkingHours(){
        return workStart.substring(0,5)+" - "+workEnd.substring(0,5);
    }

    public String getBreakHours(){

        return breakStart.substring(0,5)+ " - "+breakEnd.substring(0,5);
    }
}
