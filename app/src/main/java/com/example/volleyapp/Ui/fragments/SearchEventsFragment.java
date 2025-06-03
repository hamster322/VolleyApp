package com.example.volleyapp.Ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.volleyapp.Ui.entities.Adapters.AdapterMyEvents;
import com.example.volleyapp.databinding.FragmentSearchEventsBinding;
import com.example.volleyapp.logic.Dto.EventDto;
import com.example.volleyapp.logic.MainActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchEventsFragment extends Fragment {
    private FragmentSearchEventsBinding binding;
    private RecyclerView recyclerView;

    private MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchEventsBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        mainActivity = ((MainActivity)getActivity());

        recyclerView=binding.recyclerView;

        recyclerView.setLayoutManager(new LinearLayoutManager(mainActivity));
        mainActivity.retrofitInterface.GetAllSuitableEventsDto(mainActivity.getCurrentUser().getId()).enqueue(new Callback<List<EventDto>>() {
            @Override
            public void onResponse(Call<List<EventDto>> call, Response<List<EventDto>> response) {
                List<EventDto> events = response.body();
                recyclerView.setAdapter(new AdapterMyEvents(mainActivity.getApplicationContext(),events,position -> {
                    mainActivity.PlaceFragment(new InfromationAboutEventFragment(events.get(position),2));
                }));
            }

            @Override
            public void onFailure(Call<List<EventDto>> call, Throwable t) {

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