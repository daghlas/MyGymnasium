package com.daghlas.mygymnasium.Routines;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;

import com.daghlas.mygymnasium.R;
import com.daghlas.mygymnasium.Workouts.AbsArms.AbsArmsWorkout;

public class AbsArmsRoutines extends AppCompatActivity {

    Button startWorkout, startPreWorkout, startPostWorkout, startYoga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abs_arms_routines);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue));

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFFFF'>Routines</font>"));
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        startWorkout = findViewById(R.id.startWorkout);
        startWorkout.setOnClickListener(v -> {
            Intent intent = new Intent(AbsArmsRoutines.this, AbsArmsWorkout.class);
            startActivity(intent);
            finish();
        });
        startPreWorkout = findViewById(R.id.startPreWorkout);
        startPreWorkout.setOnClickListener(v -> {
            Intent intent = new Intent(AbsArmsRoutines.this, PreWorkoutRoutines.class);
            startActivity(intent);
        });
        startPostWorkout = findViewById(R.id.startPostWorkout);
        startPostWorkout.setOnClickListener(v -> {
            Intent intent = new Intent(AbsArmsRoutines.this, PostWorkoutRoutines.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}