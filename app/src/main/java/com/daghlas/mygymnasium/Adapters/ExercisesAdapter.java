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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daghlas.mygymnasium.Models.ExercisesModel;
import com.daghlas.mygymnasium.R;
import com.daghlas.mygymnasium.Workouts.FullBody.FullBodyInterface;

import java.util.List;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.MyViewHolder> {

    private final FullBodyInterface fullBodyInterface;
    List <ExercisesModel> exercisesModelList;
    Context context;

    public ExercisesAdapter(Context context, List<ExercisesModel> exercisesModelList, FullBodyInterface fullBodyInterface) {
        this.context = context;
        this.exercisesModelList = exercisesModelList;
        this.fullBodyInterface = fullBodyInterface;
    }


    @NonNull
    @Override
    public ExercisesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.exercises_row_layout, parent, false);
        return new ExercisesAdapter.MyViewHolder(view, fullBodyInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.exerciseName.setText(exercisesModelList.get(position).getExercise_name());
        holder.exerciseDuration.setText(exercisesModelList.get(position).getDuration());
        holder.sample.setImageResource(exercisesModelList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return exercisesModelList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView exerciseName, exerciseDuration;
        ImageView sample;

        @SuppressLint("ResourceType")
        public MyViewHolder(@NonNull View itemView, FullBodyInterface fullBodyInterface) {
            super(itemView);

            exerciseName = itemView.findViewById(R.id.exerciseName);
            exerciseDuration = itemView.findViewById(R.id.exerciseDuration);
            sample = itemView.findViewById(R.id.sample);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(fullBodyInterface != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            fullBodyInterface.onItemClick(pos);
                        }
                    }
                }
            });

        }
    }
}
