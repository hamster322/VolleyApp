package com.example.volleyapp.Ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.volleyapp.logic.MainActivity;
import com.example.volleyapp.databinding.FragmentSignUpBinding;
import com.example.volleyapp.logic.Entities.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sign_up_Fragment extends Fragment {
    private Button Button_Sign_up, Go_to_Sign_in_button;
    private EditText LoginField, PasswordField;
    private TextView ErrorMsg;

    private FragmentSignUpBinding binding;

    private MainActivity mainActivity;

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

        mainActivity = ((MainActivity)getActivity());

        Go_to_Sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sign_in_Fragment signInFragment = new Sign_in_Fragment();
                mainActivity.PlaceFragment(signInFragment);
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
    @Override
    public void onDestroyView() {
        binding=null;
        super.onDestroyView();
    }

    public void SignUpByLogPas(String login, String password){
        mainActivity.retrofitInterface.GetUserByLogin(login).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().getLogin()==""){
                    User newUser = new User(login,password);
                    mainActivity.retrofitInterface.postUser(newUser).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            mainActivity.setCurrentUser(response.body());
                            MainFragment mainFragment = new MainFragment();
                            mainActivity.PlaceFragment(mainFragment);
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            ErrorMsg.setText("Произошла ошибка "+t.getMessage());
                        }
                    });

                }
                else{
                    ErrorMsg.setText("Пользователя с таким логином уже существует");

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                ErrorMsg.setText("Произошла ошибка "+t.getMessage());
            }
        });

    }
}