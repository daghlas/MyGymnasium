package com.daghlas.mygymnasium.Models;

public class ExercisesModel {

    String exercise_name, duration;

    public ExercisesModel(String exercise_name, String duration) {
        this.exercise_name = exercise_name;
        this.duration = duration;
    }

    public String getExercise_name() {
        return exercise_name;
    }

    public String getDuration() {
        return duration;
    }
}
