package com.example.volleyapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

@Database(entities = User.class, version = 1)
public abstract class UserDataBase extends RoomDatabase {
    private static volatile UserDataBase instance;

    public abstract UserDao getUserDao();

    private static final RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Executors.newSingleThreadExecutor().execute(() -> {
                instance.getUserDao().addUser(new User("Admin","11111"));
            });
        }
    };

    public static UserDataBase getInstance(Context context){
        if (instance==null){
            synchronized (UserDataBase.class){
                if (instance==null){
                    instance= Room.databaseBuilder(context.getApplicationContext(),UserDataBase.class,"User_database").addCallback(roomCallback).build();
                }
            }
        }
        return instance;
    }

}
