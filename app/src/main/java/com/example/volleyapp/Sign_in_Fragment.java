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

import com.example.volleyapp.databinding.FragmentSignInBinding;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class Sign_in_Fragment extends Fragment {
    private Button Button_Sign_in, Go_to_Sign_up_button;
    private EditText LoginField, PasswordField;

    private TextView ErrorMsg;
    private FragmentSignInBinding binding;

    private Button DelButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignInBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        Button_Sign_in=binding.SignInButton;
        Go_to_Sign_up_button =  binding.SignUpButtonInSignIn;

        LoginField = binding.LoginInput;
        PasswordField = binding.PasswordInput;

        ErrorMsg = binding.ErrorOutput;

        DelButton = binding.DelButton;

        Go_to_Sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sign_up_Fragment signUpFragment = new Sign_up_Fragment();
                ((MainActivity)getActivity()).PlaceFragment(signUpFragment);
            }
        });

        Button_Sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = LoginField.getText().toString();
                String password = PasswordField.getText().toString();

                if (login.isEmpty() || password.isEmpty()){
                    ErrorMsg.setText("Поля не могут быть пустыми!");
                }
                else{
                    SignInByLogPas(login,password);
                }
            }
        });

        DelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = LoginField.getText().toString();
                ((MainActivity)getActivity()).executorService.execute(() -> {
                    ((MainActivity)getActivity()).UserDB.getUserDao().deleteUserByLog(login);
                });
            }
        });

        return view;
    }

    public void SignInByLogPas(String login, String password){
        ((MainActivity)getActivity()).executorService.execute(() -> {
            List<User> Users = ((MainActivity)getActivity()).UserDB.getUserDao().getUserByLogPas(login,password);
            ((MainActivity)getActivity()).mainThreadHendler.post(() -> {
                if (Users.isEmpty()){
                    ErrorMsg.setText("Пользователя с такими данными не существует!");
                }
                else{
                    ((MainActivity)getActivity()).setCurrentUser(Users.get(0));
                    MainFragment mainFragment = new MainFragment();
                    ((MainActivity)getActivity()).PlaceFragment(mainFragment);
                }
            });
        });
    }
}