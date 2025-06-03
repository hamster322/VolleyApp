package com.example.volleyapp.Ui.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.volleyapp.Ui.entities.EventForRecyclerView;
import com.example.volleyapp.Ui.entities.Adapters.AdapterMyEvents;
import com.example.volleyapp.logic.Dto.EventDto;
import com.example.volleyapp.logic.Dto.UserDto;
import com.example.volleyapp.logic.MainActivity;
import com.example.volleyapp.logic.Entities.User;
import com.example.volleyapp.databinding.FragmentMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainFragment extends Fragment {
    private ImageButton addButton, searchButton;

    private FragmentMainBinding binding;

    private User CurrentUser;

    private MainActivity mainActivity;

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        mainActivity = ((MainActivity)getActivity());

        CurrentUser=mainActivity.getCurrentUser();

        addButton = binding.addButton;
        searchButton = binding.searchButton;

        swipeRefreshLayout = binding.swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(() -> {new Handler().postDelayed(() -> {mainActivity.update();swipeRefreshLayout.setRefreshing(false);},500);});

        recyclerView=binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(mainActivity));

        mainActivity.retrofitInterface.GetUserEventsById(mainActivity.getCurrentUser().getId()).enqueue(new Callback<List<EventDto>>() {
            @Override
            public void onResponse(Call<List<EventDto>> call, Response<List<EventDto>> response) {
                List<EventDto> events = response.body();
                mainActivity.retrofitInterface.GetUserOwnedEventsById(mainActivity.getCurrentUser().getId()).enqueue(new Callback<List<EventDto>>() {
                    @Override
                    public void onResponse(Call<List<EventDto>> call, Response<List<EventDto>> response) {
                        events.addAll(response.body());
                        recyclerView.setAdapter(new AdapterMyEvents(mainActivity.getApplicationContext(),events,position -> {
                            mainActivity.PlaceFragment(new InfromationAboutEventFragment(events.get(position),1));
                        }));
                    }

                    @Override
                    public void onFailure(Call<List<EventDto>> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onFailure(Call<List<EventDto>> call, Throwable t) {

            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNewEventFragment addNewEventFragment = new AddNewEventFragment();
                mainActivity.PlaceFragment(addNewEventFragment);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchEventsFragment searchEventsFragment = new SearchEventsFragment();
                mainActivity.PlaceFragment(searchEventsFragment);
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        binding=null;
        super.onDestroyView();
    }

    public void update(){
        List<EventForRecyclerView> newEvents = new ArrayList<EventForRecyclerView>();
    }
}