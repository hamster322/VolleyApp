package com.example.volleyapp.logic;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.example.volleyapp.R;
import com.example.volleyapp.Ui.fragments.MainFragment;
import com.example.volleyapp.databinding.ActivityMainBinding;
import com.example.volleyapp.Ui.fragments.Sign_in_Fragment;
import com.example.volleyapp.logic.Entities.User;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    public Retrofit retrofit;
    public RetrofitInterface retrofitInterface;

    private User CurrentUser;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.96:8080").addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

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

    public void setCurrentUser(User user){
        this.CurrentUser=user;
    }
    public User getCurrentUser(){
        return CurrentUser;
    }

    public void update(){
        PlaceFragment(new MainFragment());
    }

}