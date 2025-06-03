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
import com.example.volleyapp.databinding.FragmentSignInBinding;
import com.example.volleyapp.logic.Entities.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sign_in_Fragment extends Fragment {
    private Button Button_Sign_in, Go_to_Sign_up_button;
    private EditText LoginField, PasswordField;

    private TextView ErrorMsg;
    private FragmentSignInBinding binding;

    private MainActivity mainActivity;

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

        mainActivity = ((MainActivity)getActivity());

        Go_to_Sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sign_up_Fragment signUpFragment = new Sign_up_Fragment();
                mainActivity.PlaceFragment(signUpFragment);
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

        return view;
    }
    @Override
    public void onDestroyView() {
        binding=null;
        super.onDestroyView();
    }

    public void SignInByLogPas(String login, String password){
        mainActivity.retrofitInterface.GetUserByLogPas(login,password).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().getLogin()==""){
                    ErrorMsg.setText("Пользователя с такими данными не обнаружено");
                }
                else{
                    mainActivity.setCurrentUser(response.body());
                    MainFragment mainFragment = new MainFragment();
                    mainActivity.PlaceFragment(mainFragment);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                ErrorMsg.setText("Произошла ошибка "+t.getMessage());
            }
        });
    }
}