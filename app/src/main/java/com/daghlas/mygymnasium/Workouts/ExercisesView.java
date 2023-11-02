package com.daghlas.mygymnasium.Workouts;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.daghlas.mygymnasium.R;

import pl.droidsonroids.gif.GifImageView;

public class ExercisesView extends AppCompatActivity {

    TextView name, duration, description;
    GifImageView image;
    Button start;
    CountDownTimer countDownTimer;

    //getExtras
    String name0, duration0, description0;
    int image0, currentTimeInSeconds;
    private boolean isCountdownPaused = false;

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

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //time trickle down method
                if (!isCountdownPaused) {
                    startCountdown();
                }

                int dur = Integer.parseInt(duration0);
                if(currentTimeInSeconds != dur){
                    pauseCountdown();
                }
            }
        });
    }

    private void startCountdown() {
        if (countDownTimer != null) {
            // Cancel the existing timer
            countDownTimer.cancel();
        }

        countDownTimer = new CountDownTimer(currentTimeInSeconds * 1000L, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                // Update the current time
                if (!isCountdownPaused) {
                    currentTimeInSeconds = (int) (millisUntilFinished / 1000);
                    updateTimeText();
                    start.setText("PAUSE");
                } else {
                    updateTimeText();
                    start.setText("RESUME");
                }
            }
            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                // Countdown timer finished
                currentTimeInSeconds = 0;
                updateTimeText();
                start.setText("FINISHED");
            }
        };

        countDownTimer.start();
    }
    private void pauseCountdown() {
        isCountdownPaused = !isCountdownPaused;
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