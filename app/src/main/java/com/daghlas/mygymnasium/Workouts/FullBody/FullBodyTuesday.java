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

import java.util.ArrayList;
import java.util.List;

public class FullBodyTuesday extends AppCompatActivity implements FullBodyInterface{

    RecyclerView recyclerView;
    ExercisesAdapter adapter;
    List<ExercisesModel> exercisesModelList;
    int[] exerciseImage = {R.drawable.jumping_jacks,
            R.drawable.squats,
            R.drawable.plank,
            R.drawable.bicycle_crunches,
            R.drawable.reverse_crucnhes,
            R.drawable.sample};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_body_tuesday);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue));

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFFFF'>Tuesday - Full Body</font>"));
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = findViewById(R.id.recyclerView);
        exercisesModelList = new ArrayList<>();
        adapter = new ExercisesAdapter(this, exercisesModelList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setUpExercisesModel();
    }

    private void setUpExercisesModel() {
        String[] exerciseName = getResources().getStringArray(R.array.tuesday_full_body);
        String[] exerciseDuration = getResources().getStringArray(R.array.tuesday_full_body_duration);
        String[] exerciseDescription = getResources().getStringArray(R.array.tuesday_full_body_description);

        for (int i = 0; i < exerciseName.length; i++) {
            exercisesModelList.add(new ExercisesModel(exerciseName[i], exerciseDuration[i], exerciseDescription[i],exerciseImage[i]));
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FullBodyTuesday.this, FullBodyWorkout.class);
        startActivity(intent);
        finish();
        super.onBackPressed();

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(FullBodyTuesday.this, ExercisesView.class);

        intent.putExtra("NAME", exercisesModelList.get(position).getExercise_name());
        intent.putExtra("DURATION", exercisesModelList.get(position).getDuration());
        intent.putExtra("DESCRIPTION", exercisesModelList.get(position).getDescription());
        intent.putExtra("IMAGE", exercisesModelList.get(position).getImage());

        startActivity(intent);
    }
}