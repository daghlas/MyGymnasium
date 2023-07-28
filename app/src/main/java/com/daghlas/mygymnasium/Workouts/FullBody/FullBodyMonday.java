package com.daghlas.mygymnasium.Workouts.FullBody;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;

import com.daghlas.mygymnasium.Adapters.ExercisesAdapter;
import com.daghlas.mygymnasium.Models.ExercisesModel;
import com.daghlas.mygymnasium.R;
import com.daghlas.mygymnasium.Routines.FullBodyRoutines;

import java.util.ArrayList;
import java.util.List;

public class FullBodyMonday extends AppCompatActivity {

    RecyclerView recyclerView;
    ExercisesAdapter adapter;
    List<ExercisesModel> exercisesModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_body_monday);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue));

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFFFF'>Monday - Full Body</font>"));
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = findViewById(R.id.recyclerView);
        exercisesModelList = new ArrayList<>();
        adapter = new ExercisesAdapter(this, exercisesModelList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FullBodyMonday.this, FullBodyWorkout.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}