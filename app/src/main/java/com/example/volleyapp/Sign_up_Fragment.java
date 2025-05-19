package com.example.volleyapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.volleyapp.databinding.FragmentSignUpBinding;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class Sign_up_Fragment extends Fragment {
    private Button Button_Sign_up, Go_to_Sign_in_button;
    private EditText LoginField, PasswordField;
    private TextView ErrorMsg;

    private FragmentSignUpBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        Button_Sign_up=binding.SignUpButton;
        Go_to_Sign_in_button=binding.SignInButtonInSignUp;

        LoginField=binding.LoginInput;
        PasswordField=binding.PasswordInput;

        ErrorMsg =binding.ErrorOutput;

        Go_to_Sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sign_in_Fragment signInFragment = new Sign_in_Fragment();
                ((MainActivity)getActivity()).PlaceFragment(signInFragment);
            }
        });

        Button_Sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = LoginField.getText().toString();
                String password = PasswordField.getText().toString();

                if (login.isEmpty() || password.isEmpty()){
                    ErrorMsg.setText("Поля не могут быть пустыми!");
                }
                else{
                    SignUpByLogPas(login,password);
                }
            }
        });

        return view;
    }

    public void SignUpByLogPas(String login, String password){
        ((MainActivity)getActivity()).executorService.execute(() -> {
            List<User> Users = ((MainActivity)getActivity()).UserDB.getUserDao().getUserByLog(login);
            ((MainActivity)getActivity()).mainThreadHendler.post(() -> {
                if (Users.isEmpty()){
                    User newUser = new User(login,password);
                    ((MainActivity)getActivity()).addUserInBackground(newUser);
//                    ((MainActivity)getActivity()).setCurrentUser(newUser);
                    MainFragment mainFragment = new MainFragment();
                    ((MainActivity)getActivity()).PlaceFragment(mainFragment);
                }
                else{
                    ErrorMsg.setText("Пользователь с таким логином уже существует!");
                }
            });
        });
    }
}