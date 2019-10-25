package com.legion1900.simpleviews.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.legion1900.simpleviews.R;

public class MainActivity extends AppCompatActivity {

    private static final int PERM_REQUEST_CODE = 42;
    private static final String PERMISSION_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;

    private Intent viewDemo;
    private Intent viewGroupDemo;
    private Intent mapDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewDemo = new Intent(this, CircleDemo.class);
        viewGroupDemo = new Intent(this, ReverseLayoutDemo.class);
        mapDemo = new Intent(this, MapsActivity.class);
    }

    public void onCircleDemoClick(View view) { startActivity(viewDemo); }

    public void onViewGroupDemoClick(View view) { startActivity(viewGroupDemo); }

    public void onMapDemoClick(View view) {
        Log.d("Test", isPermitted(PERMISSION_LOCATION) + "");
        if (isPermitted(PERMISSION_LOCATION))
            startActivity(mapDemo);
        else askForPermission(PERMISSION_LOCATION);
    }

    private boolean isPermitted(String permission) {
        return ContextCompat.checkSelfPermission(this, permission)
                == PackageManager.PERMISSION_GRANTED;
    }

    /*
    * Asks for dangerous permission and returns the result.
    * */
    private void askForPermission(String permission) {
        ActivityCompat.requestPermissions(
                this,
                new String[] {Manifest.permission.ACCESS_COARSE_LOCATION},
                PERM_REQUEST_CODE
        );
    }
}
