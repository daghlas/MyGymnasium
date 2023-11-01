package com.daghlas.mygymnasium.Workouts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.daghlas.mygymnasium.R;

import pl.droidsonroids.gif.GifImageView;

public class ExercisesView extends AppCompatActivity {

    TextView name, duration, description;
    GifImageView image;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_view);

        name = findViewById(R.id.exerciseName);
        duration = findViewById(R.id.exerciseDuration);
        description = findViewById(R.id.description);
        image = findViewById(R.id.exerciseGif);
        start = findViewById(R.id.start);

        String name0 = getIntent().getStringExtra("NAME");
        String duration0 = getIntent().getStringExtra("DURATION");
        String description0 = getIntent().getStringExtra("DESCRIPTION");
        int image0 = getIntent().getIntExtra("IMAGE",0);

        name.setText(name0);
        duration.setText(duration0);
        description.setText(description0);
        image.setImageResource(image0);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //time trickle down
            }
        });
    }
}