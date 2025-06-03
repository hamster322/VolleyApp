package com.example.volleyapp.Ui.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.volleyapp.databinding.FragmentAddNewEventBinding;
import com.example.volleyapp.logic.Entities.EventPost;
import com.example.volleyapp.logic.MainActivity;
import com.example.volleyapp.logic.Entities.User;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddNewEventFragment extends Fragment {

    private Button changeDateButton, changeTimeButton, postButton;

    private EditText placeField;

    private TextView timeText,errorMsg;

    private FragmentAddNewEventBinding binding;

    private MainActivity mainActivity;

    Calendar dateAndTime = Calendar.getInstance();

    private boolean isTimeSet=false,isDateSet=false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddNewEventBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        mainActivity=((MainActivity)getActivity());

        changeDateButton = binding.changeDateButton;
        changeTimeButton = binding.changeTimeButton;
        postButton = binding.PostButton;

        placeField = binding.placeEditText;

        timeText = binding.timeText;
        errorMsg = binding.ErrorMsg;

        User currentUser = mainActivity.getCurrentUser();

        changeDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(mainActivity,d,dateAndTime.get(Calendar.YEAR),dateAndTime.get(Calendar.MONTH),dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        changeTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(mainActivity,t,dateAndTime.get(Calendar.HOUR_OF_DAY),dateAndTime.get(Calendar.MINUTE),true).show();
            }
        });

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String place = placeField.getText().toString();
                if (place.isEmpty()||!isDateSet||!isTimeSet){
                    errorMsg.setText("Заполнены не все поля");
                }
                else{
                    mainActivity.retrofitInterface.AddEvent(new EventPost(currentUser.getId(),place, dateAndTime.getTimeInMillis())).enqueue(new Callback<Integer>() {
                        @Override
                        public void onResponse(Call<Integer> call, Response<Integer> response) {
                            mainActivity.update();
                        }

                        @Override
                        public void onFailure(Call<Integer> call, Throwable t) {
                        }
                    });
                }
                mainActivity.update();
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        binding=null;
        super.onDestroyView();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            dateAndTime.set(Calendar.YEAR,year);
            dateAndTime.set(Calendar.MONTH,month);
            dateAndTime.set(Calendar.DAY_OF_MONTH,month);
            isDateSet=true;
            setTextToTimeText();
        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hour, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY,hour);
            dateAndTime.set(Calendar.MINUTE,minute);
            isTimeSet=true;
            setTextToTimeText();
        }
    };


    private void setTextToTimeText(){
        timeText.setText("Время: "+DateUtils.formatDateTime(mainActivity,dateAndTime.getTimeInMillis(),DateUtils.FORMAT_SHOW_DATE|DateUtils.FORMAT_SHOW_YEAR|DateUtils.FORMAT_SHOW_TIME));
    }
}