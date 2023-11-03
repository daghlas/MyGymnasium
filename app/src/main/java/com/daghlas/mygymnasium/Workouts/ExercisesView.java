package com.daghlas.mygymnasium.Workouts;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.daghlas.mygymnasium.R;

import pl.droidsonroids.gif.GifImageView;

public class ExercisesView extends AppCompatActivity {

    TextView name, duration, description;
    GifImageView image;
    ImageView back;
    Button start;
    //getExtras
    String name0, duration0, description0;
    //timer
    int image0, currentTimeInSeconds;
    private boolean isCountdownRunning = false;
    private Handler countdownHandler;
    private Runnable countdownRunnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_view);

        name = findViewById(R.id.exerciseName);
        duration = findViewById(R.id.exerciseDuration);
        description = findViewById(R.id.description);
        image = findViewById(R.id.exerciseGif);
        start = findViewById(R.id.start);

        name0 = getIntent().getStringExtra("NAME");
        duration0 = getIntent().getStringExtra("DURATION");
        description0 = getIntent().getStringExtra("DESCRIPTION");
        image0 = getIntent().getIntExtra("IMAGE",0);

        //string int conversion & duration display in 00:00 format
        assert duration0 != null;
        currentTimeInSeconds = Integer.parseInt(duration0);
        formatTime(currentTimeInSeconds);

        name.setText(name0);
        duration.setText(formatTime(currentTimeInSeconds));
        description.setText(description0);
        image.setImageResource(image0);

        start.setOnClickListener(v -> {
            //time trickle down method
            if (isCountdownRunning) {
                pauseCountdown();
            } else {
                startCountdown();
            }
        });

        back = findViewById(R.id.backButton);
        back.setOnClickListener(v -> onBackPressed());
    }

    @SuppressLint("SetTextI18n")
    private void startCountdown() {
        isCountdownRunning = true;
        start.setText("PAUSE");
        countdownHandler = new Handler(Looper.getMainLooper());
        countdownRunnable = new Runnable() {
            @Override
            public void run() {
                if (currentTimeInSeconds >= 0) {
                    updateTimeText();
                    currentTimeInSeconds--;
                    countdownHandler.postDelayed(this, 1000); // Update every 1 second
                } else {
                    isCountdownRunning = false;
                    start.setText("FINISHED");
                }
            }
        };

        countdownHandler.post(countdownRunnable);
    }
    @SuppressLint("SetTextI18n")
    private void pauseCountdown() {
        isCountdownRunning = false;
        start.setText("RESUME");
        if (countdownHandler != null && countdownRunnable != null) {
            countdownHandler.removeCallbacks(countdownRunnable);
        }
    }

    private void updateTimeText() {
        duration.setText(formatTime(currentTimeInSeconds));
    }

    @SuppressLint("DefaultLocale")
    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }
}