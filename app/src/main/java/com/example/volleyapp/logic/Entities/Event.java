package com.example.volleyapp.logic.Entities;

import java.time.LocalDateTime;
import java.util.List;

public class Event {
    private Long id;
    private Long owner_id;
    private List<Long> users_ids;
    private LocalDateTime time;
    private String place;

    public Event(Long owner_id, List<Long> users_ids, LocalDateTime time, String place) {
        this.owner_id = owner_id;
        this.users_ids = users_ids;
        this.time = time;
        this.place = place;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Long owner_id) {
        this.owner_id = owner_id;
    }

    public List<Long> getUsers_ids() {
        return users_ids;
    }

    public void setUsers_ids(List<Long> users_ids) {
        this.users_ids = users_ids;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
