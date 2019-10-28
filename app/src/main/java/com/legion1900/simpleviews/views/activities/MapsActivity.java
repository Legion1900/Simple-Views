package com.legion1900.simpleviews.views.activities;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.legion1900.simpleviews.R;
import com.legion1900.simpleviews.views.utils.GeoUtils;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int MAX_RADIUS = 10000;
    private static final int MIN_RADIUS = 5000;
    private static final int MARKERS_NUMBER = 10;

    private GoogleMap mMap;
    private FusedLocationProviderClient locationProvider;

    private Marker userMarker;
    private Marker[] rndMarkers = new Marker[MARKERS_NUMBER];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        initLocationProvider();
    }

    /*
     * Initiates fused location provider and starts async query to get location.
     * */
    private void initLocationProvider() {
        locationProvider = LocationServices.getFusedLocationProviderClient(this);
        Task<Location> task = locationProvider.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                onLocationAcquired(location);
            }
        });
    }

    private void onLocationAcquired(Location location) {
        if (location == null) return;
        String titleUser = "You are here";
        double lat = location.getLatitude();
        double lng = location.getLongitude();
        LatLng userLocation = new LatLng(lat, lng);
        // Add current location marker and move camera to it
        userMarker = addMarker(userLocation, titleUser);
        moveCameraTo(userLocation);
    }

    private Marker addMarker(LatLng location, String title) {
        return mMap.addMarker(new MarkerOptions().position(location).title(title));
    }

    private void moveCameraTo(LatLng location) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12));
    }

    public void onAddRandomMarkersClick(View view) {
        // User LanLat in meters.
        LatLng center = userMarker.getPosition();
        String noTitle = "";
        for (int i = 0; i < MARKERS_NUMBER; i++) {
            LatLng rndDot = GeoUtils.rndDotInRadius(center, MIN_RADIUS, MAX_RADIUS);
            rndMarkers[i] = addMarker(rndDot, noTitle);
        }
    }
}
