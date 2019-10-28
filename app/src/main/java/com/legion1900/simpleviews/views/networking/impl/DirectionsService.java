package com.legion1900.simpleviews.views.networking.impl;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface DirectionsService {
    @GET("maps/api/directions/json")
    Call<String> query(@QueryMap Map<String, String> options);
}
