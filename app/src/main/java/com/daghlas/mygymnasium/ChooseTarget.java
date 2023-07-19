package com.daghlas.mygymnasium;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseTarget extends AppCompatActivity {

    CardView bellyFat, keepFit, buildMuscle;
    TextView belly, fit, muscle;
    ProgressBar belly_progressBar, fit_progressBar, muscle_progressBar;
    Button cancel;
    private boolean isClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_target);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));

        bellyFat = findViewById(R.id.belly_fat);
        keepFit = findViewById(R.id.keep_fit);
        buildMuscle = findViewById(R.id.build_muscle);

        belly = findViewById(R.id.belly_fat_text);
        fit = findViewById(R.id.keep_fit_text);
        muscle = findViewById(R.id.build_muscle_text);

        belly_progressBar = findViewById(R.id.belly_fat_progress);
        fit_progressBar = findViewById(R.id.keep_fit_progress);
        muscle_progressBar = findViewById(R.id.build_muscle_progress);

        cancel = findViewById(R.id.cancel);

        //VISIBILITY CHECKS
        belly_progressBar.setVisibility(View.INVISIBLE);
        fit_progressBar.setVisibility(View.INVISIBLE);
        muscle_progressBar.setVisibility(View.INVISIBLE);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        bellyFat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClicked){
                    bellyFat.setCardBackgroundColor(getResources().getColor(R.color.grey));
                }else {
                    bellyFat.setCardBackgroundColor(getResources().getColor(R.color.blue));
                    belly_progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
                    belly_progressBar.setVisibility(View.VISIBLE);
                    //next activity
                    new Handler().postDelayed(()->{
                        Intent intent = new Intent(ChooseTarget.this, YourWeight.class);
                        startActivity(intent);
                        finish();
                        belly_progressBar.setVisibility(View.INVISIBLE);
                    },2000);
                }
                isClicked = !isClicked;
            }
        });

        keepFit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                if (isClicked){
                    keepFit.setCardBackgroundColor(getResources().getColor(R.color.grey));
                }else {
                    keepFit.setCardBackgroundColor(getResources().getColor(R.color.blue));
                    fit_progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
                    fit_progressBar.setVisibility(View.VISIBLE);
                    //next activity
                    new Handler().postDelayed(()->{
                        Intent intent = new Intent(ChooseTarget.this, YourWeight.class);
                        startActivity(intent);
                        finish();
                        fit_progressBar.setVisibility(View.INVISIBLE);
                    },2000);
                }
                isClicked = !isClicked;

                 */
                Toast.makeText(ChooseTarget.this, "Coming soon", Toast.LENGTH_SHORT).show();
            }
        });

        buildMuscle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                if (isClicked){
                    buildMuscle.setCardBackgroundColor(getResources().getColor(R.color.grey));
                }else {
                    buildMuscle.setCardBackgroundColor(getResources().getColor(R.color.blue));
                    muscle_progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
                    muscle_progressBar.setVisibility(View.VISIBLE);
                    //next activity
                    new Handler().postDelayed(()->{
                        Intent intent = new Intent(ChooseTarget.this, YourWeight.class);
                        startActivity(intent);
                        finish();
                        muscle_progressBar.setVisibility(View.INVISIBLE);
                    },2000);
                }
                isClicked = !isClicked;
                 */
                Toast.makeText(ChooseTarget.this, "Coming soon", Toast.LENGTH_SHORT).show();
            }
        });

    }
}