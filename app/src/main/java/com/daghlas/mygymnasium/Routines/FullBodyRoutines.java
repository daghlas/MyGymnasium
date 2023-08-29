package com.daghlas.mygymnasium.Routines;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;

import com.daghlas.mygymnasium.MainActivity;
import com.daghlas.mygymnasium.R;
import com.daghlas.mygymnasium.Workouts.FullBody.FullBodyWorkout;

public class FullBodyRoutines extends AppCompatActivity {

    Button startWorkout, startPreWorkout, startPostWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_body_routines);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue));

        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFFFF'>Routines</font>"));
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        startWorkout = findViewById(R.id.startWorkout);
        startWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FullBodyRoutines.this, FullBodyWorkout.class);
                startActivity(intent);
                finish();
            }
        });
        startPreWorkout = findViewById(R.id.startPreWorkout);
        startPreWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FullBodyRoutines.this, PreWorkoutRoutines.class);
                startActivity(intent);
                //finish();
            }
        });
        startPostWorkout = findViewById(R.id.startPostWorkout);
        startPostWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FullBodyRoutines.this, PostWorkoutRoutines.class);
                startActivity(intent);
                //finish();
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}