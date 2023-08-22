package com.daghlas.mygymnasium.Models;

public class ExercisesModel {

    String exercise_name, duration;
    int image;

    public ExercisesModel(String exercise_name, String duration, int image) {
        this.exercise_name = exercise_name;
        this.duration = duration;
        this.image = image;
    }

    public String getExercise_name() {
        return exercise_name;
    }

    public String getDuration() {
        return duration;
    }

    public int getImage() {
        return image;
    }
}
