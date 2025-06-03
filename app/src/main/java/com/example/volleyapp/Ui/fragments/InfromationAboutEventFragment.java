package com.example.volleyapp.Ui.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.volleyapp.Ui.entities.Adapters.AdapterListUsers;
import com.example.volleyapp.databinding.FragmentInfromationAboutEventBinding;
import com.example.volleyapp.logic.Dto.EventDto;
import com.example.volleyapp.logic.MainActivity;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfromationAboutEventFragment extends Fragment {
    private int type;
    private EventDto currentEvent;

    private Button joinButton;
    private TextView ownerView,placeView,timeView;
    private RecyclerView listUsersView;

    private FragmentInfromationAboutEventBinding binding;

    private MainActivity mainActivity;

    public InfromationAboutEventFragment(EventDto currentEvent, int type) {
        this.currentEvent = currentEvent;
        this.type=type;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInfromationAboutEventBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        mainActivity = ((MainActivity)getActivity());

        ownerView=binding.eventOwner;
        placeView=binding.eventPlace;
        timeView=binding.eventTime;

        joinButton=binding.JoinButton;

        listUsersView=binding.recyclerView;

        ownerView.setText("Создатель: "+currentEvent.getOwner().getLogin());
        placeView.setText("Место: "+currentEvent.getPlace());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            timeView.setText("Время: "+ Instant.ofEpochMilli(currentEvent.getTimeInMilles()).atZone(ZoneId.of("Europe/Moscow")).toLocalDateTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG,FormatStyle.SHORT)));
        }

        listUsersView.setLayoutManager(new LinearLayoutManager(mainActivity));
        listUsersView.setAdapter(new AdapterListUsers(mainActivity.getApplicationContext(),currentEvent.getUsers()));

        if (type==1){
            joinButton.setVisibility(View.GONE);
        }

        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.retrofitInterface.AddParticipiantToEvent(mainActivity.getCurrentUser().getId(),currentEvent.getId()).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        mainActivity.update();
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {

                    }
                });
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        binding=null;
        super.onDestroyView();
    }
}