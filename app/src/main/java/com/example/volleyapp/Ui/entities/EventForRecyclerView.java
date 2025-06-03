package com.example.volleyapp.Ui.entities;

import java.time.LocalDateTime;

public class EventForRecyclerView {
    String place;
    String time;
    String owner;

    public EventForRecyclerView(String place, String time, String owner) {
        this.place = place;
        this.time = time;
        this.owner = owner;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
