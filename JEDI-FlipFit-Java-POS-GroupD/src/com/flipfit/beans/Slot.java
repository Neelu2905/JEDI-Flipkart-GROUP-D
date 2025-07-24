package com.flipfit.beans;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Slot {
    private Long gymID; // Correctly Long
    private int capacity;
    private int vacant;
    private Date date;
    private Time time;

    // FIX: Populate the constructor with assignments
    public Slot(Long gymID, int capacity, int vacant, Date date, Time time) {
        this.gymID = gymID; // Assign the parameter to the field
        this.capacity = capacity; // Assign the parameter to the field
        this.vacant = vacant;   // Assign the parameter to the field
        this.date = date;       // Assign the parameter to the field
        this.time = time;       // Assign the parameter to the field
    }

    public Long getGymID() {
        return gymID;
    }

    public void setGymID(Long gymID) {
        this.gymID = gymID;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getVacant() {
        return vacant;
    }

    public void setVacant(int vacant) {
        this.vacant = vacant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    // FIX 2: Add @Override toString() to print meaningful data
    @Override
    public String toString() {
        return "Slot{" +
                "gymID=" + gymID +
                ", capacity=" + capacity +
                ", vacant=" + vacant +
                ", date=" + (date != null ? new SimpleDateFormat("yyyy-MM-dd").format(date) : "null") + // Format date
                ", time=" + (time != null ? time.toString() : "null") + // Time format is usually fine
                '}';
    }
}