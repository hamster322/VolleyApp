package com.example.volleyapp.logic.Entities;

public class EventPost {
    private Long owner_id;
    private String place;
    private Long dateTime;

    public EventPost(Long owner_id, String place, Long dateTime) {
        this.owner_id = owner_id;
        this.place = place;
        this.dateTime = dateTime;
    }
}
