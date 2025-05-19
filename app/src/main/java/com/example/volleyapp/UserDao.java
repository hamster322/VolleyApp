package com.example.volleyapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    public void addUser(User user);
    @Update
    public void updateUser(User user);
    @Delete
    public void deleteUser(User user);
    @Query("select * from user")
    public List<User> getAllUsers();
    @Query("select * from user where User_id==:user_id")
    public List<User> getUserById(int user_id);
    @Query("select * from user where Login==:login and Password==:password")
    public List<User> getUserByLogPas(String login, String password);
    @Query("select * from user where Login==:login")
    public List<User> getUserByLog(String login);
    @Query("delete from user where Login==:login")
    public void deleteUserByLog(String login);
}
