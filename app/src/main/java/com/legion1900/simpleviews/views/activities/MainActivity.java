package com.legion1900.simpleviews.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.legion1900.simpleviews.R;

public class MainActivity extends AppCompatActivity {

    private Intent viewDemo;
    private Intent viewGroupDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewDemo = new Intent(this, CircleDemo.class);
        viewGroupDemo = new Intent(this, ReverseLayoutDemo.class);
    }

    public void onCircleDemoClick(View view) { startActivity(viewDemo); }

    public void onViewGroupDemoClick(View view) { startActivity(viewGroupDemo); }
}
