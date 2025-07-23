package com.flipfit.beans;

import java.sql.Time;
import java.util.Date;

public class Slot {
    private int gymID;
    private int capacity;
    private int vacant;
    private Date date;
    private Time time;

    public Slot(int slotGymId, int capacity, int vacant, Date date, Time time) {
    }

    public int getGymID() {
        return gymID;
    }

    public void setGymID(int gymID) {
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
}