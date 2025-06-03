package com.example.volleyapp.logic;

import com.example.volleyapp.logic.Dto.EventDto;
import com.example.volleyapp.logic.Entities.EventPost;
import com.example.volleyapp.logic.Entities.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitInterface {
    @GET("/GetUser/{id}")
    Call<User> GetUserById(@Path("id") Long id);

    @POST("/PostUser")
    Call<User> postUser(@Body User userPost);

    @GET("/GetUserByLogin/{login}")
    Call<User> GetUserByLogin(@Path("login") String login);

    @GET("/GetUserByLogPas/{login}/{password}")
    Call<User> GetUserByLogPas(@Path("login") String login, @Path("password") String password);

    @POST("/Events")
    Call<Integer> AddEvent(@Body EventPost eventPost);

    @GET("/EventsDto")
    Call<List<EventDto>> GetAllEventsDto();

    @GET("/Events/AddParticipant/{id_user}/{id_event}")
    Call<Integer> AddParticipiantToEvent(@Path("id_user") Long id_user, @Path("id_event") Long id_event);

    @GET("/GetUserEventsById/{id}")
    Call<List<EventDto>> GetUserEventsById(@Path("id")Long id);

    @GET("/GetUserOwnedEventsById/{id}")
    Call<List<EventDto>> GetUserOwnedEventsById(@Path("id")Long id);

    @GET("/GetUserAvailableEvents/{id}")
    Call<List<EventDto>> GetAllSuitableEventsDto(@Path("id")Long id);
}
