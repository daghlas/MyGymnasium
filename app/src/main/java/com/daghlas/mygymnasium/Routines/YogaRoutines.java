package com.daghlas.mygymnasium.Routines;

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
import com.daghlas.mygymnasium.Workouts.ExercisesView;
import com.daghlas.mygymnasium.Workouts.WorkoutsInterface;

import java.util.ArrayList;
import java.util.List;

public class YogaRoutines extends AppCompatActivity implements WorkoutsInterface {

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
        setContentView(R.layout.activity_yoga_routines);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue));

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFFFF'>Yoga</font>"));
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
        String[] exerciseName = getResources().getStringArray(R.array.yoga_name);
        String[] exerciseDuration = getResources().getStringArray(R.array.yoga_duration);
        String[] exerciseDescription = getResources().getStringArray(R.array.yoga_description);

        for (int i = 0; i < exerciseName.length; i++) {
            exercisesModelList.add(new ExercisesModel(exerciseName[i], exerciseDuration[i], exerciseDescription[i],exerciseImage[i]));
        }
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(YogaRoutines.this, ExercisesView.class);

        intent.putExtra("NAME", exercisesModelList.get(position).getExercise_name());
        intent.putExtra("DURATION", exercisesModelList.get(position).getDuration());
        intent.putExtra("DESCRIPTION", exercisesModelList.get(position).getDescription());
        intent.putExtra("IMAGE", exercisesModelList.get(position).getImage());

        startActivity(intent);
    }
}