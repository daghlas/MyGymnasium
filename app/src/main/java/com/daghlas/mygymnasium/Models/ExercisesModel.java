package com.daghlas.mygymnasium.Models;

public class ExercisesModel {

    String exercise_name, duration, description;
    int image;

    public ExercisesModel(String exercise_name, String duration, String description, int image) {
        this.exercise_name = exercise_name;
        this.duration = duration;
        this.description = description;
        this.image = image;
    }

    public String getExercise_name() {
        return exercise_name;
    }

    public String getDuration() {
        return duration;
    }

    public String getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }
}
