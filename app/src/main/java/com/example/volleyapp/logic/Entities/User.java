package com.example.volleyapp.logic.Entities;

import com.example.volleyapp.logic.Dto.EventDto;

import java.util.List;

public class User {
    Long id;
    String login,password;
//    List<Long> events_ids;
//    List<Long> owned_events_ids;
    List<EventDto> events;
    List<EventDto> ownedEvents;


    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public User(String login, String password, List<Long> events_ids) {
//        this.login = login;
//        this.password = password;
//        this.events_ids = events_ids;
//    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public List<Long> getEvents_ids() {
//        return events_ids;
//    }

//    public void setEvents_ids(List<Long> events_ids) {
//        this.events_ids = events_ids;
//public List<Long> getOwned_events_ids() {
//    return owned_events_ids;
//}

//    }

//    public void setOwned_events_ids(List<Long> owned_events_ids) {
//        this.owned_events_ids = owned_events_ids;
//    }

    public List<EventDto> getEvents() {
        return events;
    }

    public void setEvents(List<EventDto> events) {
        this.events = events;
    }
}
