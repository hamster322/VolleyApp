package com.example.volleyapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "User")
public class User {
    @ColumnInfo(name = "User_id")
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "Login")
    String login;
    @ColumnInfo(name = "Password")
        String password;

    public User(){}
    public User(String login, String password){
    this.login=login;
    this.password=password;
    this.id=0;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

}
