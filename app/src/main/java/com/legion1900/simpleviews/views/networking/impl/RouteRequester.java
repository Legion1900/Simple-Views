package com.legion1900.simpleviews.views.networking.impl;

import com.google.android.gms.maps.model.LatLng;
import com.legion1900.simpleviews.BuildConfig;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RouteRequester {

    public static final String MODE_WALKING = "walking";
    public static final String MODE_DRIVING = "driving";

    private static final String BASE_URL = "https://maps.googleapis.com";

    private static final String KEY_ORIGIN = "origin";
    private static final String KEY_DEST = "destination";
    private static final String KEY_MODE = "mode";
    private static final String KEY_API_KEY = "key";

    private Callback<String> callback;
    private DirectionsService service;

    public RouteRequester(Callback<String> callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        service = retrofit.create(DirectionsService.class);
        this.callback = callback;
    }

    public Map<String, String> buildQuery(LatLng origin, LatLng dest, String mode) {
        Map<String, String> options = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        options.put(KEY_ORIGIN, latLngToStr(origin, builder));
        options.put(KEY_DEST, latLngToStr(dest, builder));
        options.put(KEY_MODE, mode);
        String apiKey = BuildConfig.DirectionsApiKey;
        options.put(KEY_API_KEY, apiKey);
        return options;
    }

    public void query(Map<String, String> options) {
        Call<String> news = service.query(options);
        news.enqueue(callback);
    }

    private String latLngToStr(LatLng place, StringBuilder builder) {
        final char separator = ',';
        builder.setLength(0);
        builder.append(place.latitude);
        builder.append(separator);
        builder.append(place.longitude);
        return builder.toString();
    }
}
