package com.legion1900.simpleviews.views.activities;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.legion1900.simpleviews.R;
import com.legion1900.simpleviews.views.customviews.CustomReverseLayout;

import java.util.Random;

public class ReverseLayoutDemo extends AppCompatActivity {
    private Random generator = new Random();
    private LayoutInflater inflater;

    private CustomReverseLayout reverseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reverse_layout_demo);

        inflater = LayoutInflater.from(this);
        reverseList = findViewById(R.id.reverse_list);
    }

    public void onAddElementClick(View view) {
        View randomView = getRandomView();
        int index = reverseList.getChildCount();
        reverseList.addView(randomView, index);
    }

    private View getRandomView() {
        final int max = 255;
        int red = generator.nextInt(max);
        int green = generator.nextInt(max);
        int blue = generator.nextInt(max);
        View view = inflater.inflate(R.layout.random_item, reverseList, false);
        view.setBackgroundColor(Color.rgb(red, green, blue));
        return view;
    }
}
