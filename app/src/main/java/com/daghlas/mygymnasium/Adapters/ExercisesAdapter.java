package com.daghlas.mygymnasium.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daghlas.mygymnasium.Models.ExercisesModel;
import com.daghlas.mygymnasium.R;
import com.daghlas.mygymnasium.Workouts.WorkoutsInterface;

import java.util.List;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.MyViewHolder> {

    private final WorkoutsInterface workoutsInterface;
    List<ExercisesModel> exercisesModelList;
    Context context;

    public ExercisesAdapter(Context context, List<ExercisesModel> exercisesModelList, WorkoutsInterface workoutsInterface) {
        this.context = context;
        this.exercisesModelList = exercisesModelList;
        this.workoutsInterface = workoutsInterface;
    }


    @NonNull
    @Override
    public ExercisesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.exercises_row_layout, parent, false);
        return new ExercisesAdapter.MyViewHolder(view, workoutsInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.exerciseName.setText(exercisesModelList.get(position).getExercise_name());
        holder.exerciseDuration.setText(formatTime(Integer.parseInt(exercisesModelList.get(position).getDuration())));
        holder.sample.setImageResource(exercisesModelList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return exercisesModelList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView exerciseName, exerciseDuration;
        ImageView sample;

        @SuppressLint("ResourceType")
        public MyViewHolder(@NonNull View itemView, WorkoutsInterface workoutsInterface) {
            super(itemView);

            exerciseName = itemView.findViewById(R.id.exerciseName);
            exerciseDuration = itemView.findViewById(R.id.exerciseDuration);
            sample = itemView.findViewById(R.id.sample);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (workoutsInterface != null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            workoutsInterface.onItemClick(pos);
                        }
                    }
                }
            });

        }
    }

    @SuppressLint("DefaultLocale")
    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }
}
