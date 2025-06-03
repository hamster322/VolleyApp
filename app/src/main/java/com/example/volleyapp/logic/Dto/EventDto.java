package com.example.volleyapp.logic.Dto;

import java.util.List;

public class EventDto {
    Long id;
    UserDto owner;
    String place;
    Long timeInMilles;
    List<UserDto> users;

    public EventDto(UserDto owner, String place, Long timeInMilles) {
        this.owner = owner;
        this.place = place;
        this.timeInMilles = timeInMilles;
    }

    public EventDto(UserDto owner, String place, Long timeInMilles, List<UserDto> users) {
        this.owner = owner;
        this.place = place;
        this.timeInMilles = timeInMilles;
        this.users = users;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }

    public UserDto getOwner() {
        return owner;
    }

    public void setOwner(UserDto owner) {
        this.owner = owner;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Long getTimeInMilles() {
        return timeInMilles;
    }

    public void setTimeInMilles(Long timeInMilles) {
        this.timeInMilles = timeInMilles;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
