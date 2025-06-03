package com.example.volleyapp.logic.Dto;

public class UserDto {
    private long id;
    private String login;

    public UserDto(long id, String login) {
        this.id = id;
        this.login = login;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
