package com.daghlas.mygymnasium;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;

public class YourHeight extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_height);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
    }
}