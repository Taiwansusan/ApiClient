package com.example.apiclient.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("users")
    Call<List<UserData>> getAllUser();
    @GET("users/{username}")
    Call<UserDataDetail> getUser(@Path("username") String username);
}
