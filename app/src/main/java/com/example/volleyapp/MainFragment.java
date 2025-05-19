package com.example.volleyapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.volleyapp.databinding.FragmentMainBinding;


public class MainFragment extends Fragment {

//    private TextView log,pas;

    private FragmentMainBinding binding;

    private User CurrentUser;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        CurrentUser=((MainActivity)getActivity()).getCurrentUser();

//        log=binding.loginTest;
//        pas=binding.passwordTest;
//
//        log.setText(CurrentUser.getLogin());
//        pas.setText(CurrentUser.getPassword());


        return view;
    }
}