package com.daghlas.mygymnasium.Workouts.FullBody;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.daghlas.mygymnasium.R;

import pl.droidsonroids.gif.GifImageView;

public class ExercisesView extends AppCompatActivity {

    TextView name, duration, description;
    GifImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_view);

        name = findViewById(R.id.exerciseName);
        duration = findViewById(R.id.exerciseDuration);
        description = findViewById(R.id.description);
        image = findViewById(R.id.exerciseGif);

        String name0 = getIntent().getStringExtra("NAME");
        String duration0 = getIntent().getStringExtra("DURATION");
        String description0 = getIntent().getStringExtra("DESCRIPTION");
        int image0 = getIntent().getIntExtra("IMAGE",0);

        name.setText(name0);
        duration.setText(duration0);
        description.setText(description0);
        image.setImageResource(image0);
    }
}