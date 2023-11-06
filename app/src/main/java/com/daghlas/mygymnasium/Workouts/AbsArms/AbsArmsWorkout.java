package com.daghlas.mygymnasium.Workouts.AbsArms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;

import com.daghlas.mygymnasium.R;
import com.daghlas.mygymnasium.Routines.AbsArmsRoutines;

public class AbsArmsWorkout extends AppCompatActivity {

    Button monday, tuesday, wednesday, thursday, friday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abs_arms_workout);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue));

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFFFF'>Abs & Arms Workouts</font>"));
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        monday = findViewById(R.id.startMonday);
        monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AbsArmsWorkout.this, AbsArmsMonday.class);
                startActivity(intent);
                finish();
            }
        });

        friday = findViewById(R.id.startFriday);
        friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AbsArmsWorkout.this, AbsArmsMonday.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AbsArmsWorkout.this, AbsArmsRoutines.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}