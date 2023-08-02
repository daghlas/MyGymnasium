package com.daghlas.mygymnasium.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daghlas.mygymnasium.Models.ExercisesModel;
import com.daghlas.mygymnasium.R;

import java.util.List;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.MyViewHolder> {

    List <ExercisesModel> exercisesModelList;
    Context context;

    public ExercisesAdapter(Context context, List<ExercisesModel> exercisesModelList) {
        this.context = context;
        this.exercisesModelList = exercisesModelList;
    }


    @NonNull
    @Override
    public ExercisesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.exercises_row_layout, parent, false);
        return new ExercisesAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView sample;

        @SuppressLint("ResourceType")
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            sample = itemView.findViewById(R.id.sample);
            sample.setBackgroundResource(R.raw.sample);

            //AnimationDrawable animationDrawable = (AnimationDrawable) sample.getBackground();
            //animationDrawable.start();

        }
    }
}
