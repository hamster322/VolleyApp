package com.example.volleyapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.example.volleyapp.databinding.ActivityMainBinding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    public UserDataBase UserDB;
    public ExecutorService executorService;
    public Handler mainThreadHendler;

    private User CurrentUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UserDB = UserDataBase.getInstance(getApplicationContext());
        executorService = Executors.newSingleThreadExecutor();
        mainThreadHendler = new Handler(Looper.getMainLooper());

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Sign_in_Fragment signInFragment = new Sign_in_Fragment();

        PlaceFragment(signInFragment);



    }

    public void PlaceFragment(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main,fragment);
        ft.addToBackStack(null);
        ft.commit();
//        Toast.makeText(getApplicationContext(),"Тест нового фрагмента",Toast.LENGTH_LONG).show();
    }

    public void addUserInBackground(User user){
        executorService.execute(() -> {
            UserDB.getUserDao().addUser(user);
        });
    }

    public void setCurrentUser(User user){
        this.CurrentUser=user;
    }
    public User getCurrentUser(){
        return CurrentUser;
    }

}