package com.daghlas.mygymnasium.BMI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.daghlas.mygymnasium.MainActivity;
import com.daghlas.mygymnasium.R;

public class BMI extends AppCompatActivity {

    ImageView back, graph;
    SharedPreferences preferences;
    TextView targetBMI,currentBMI, bmiStatement;
    Button next;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));

        targetBMI = findViewById(R.id.targetBMI);
        currentBMI = findViewById(R.id.currentBMI);
        bmiStatement = findViewById(R.id.bmiStatement);

        preferences = getSharedPreferences("VALUES", MODE_PRIVATE);
        int yourWeight = preferences.getInt("yourWeight", 70);
        int targetWeight = preferences.getInt("targetWeight", 70);
        int yourHeight = preferences.getInt("yourHeight", 150);

        //target BMI formula & display
        double height_in_meters = yourHeight / (double)100;
        double targetBMI_formula = (targetWeight) / ((height_in_meters)*2); //formula
        String finalBMI = String.format("%.1f", targetBMI_formula); //format answer to 1dp
        targetBMI.setText(finalBMI); //display calculated result

        //current BMI formula & display
        double currentBMI_formula = (yourWeight) / ((height_in_meters)*2);
        String currentBMI_final = String.format("%.1f", currentBMI_formula);
        currentBMI.setText(currentBMI_final);

        //BMI categorizations and text display
        if(currentBMI_formula < 18.5){
            bmiStatement.setText(R.string.underweight);
        } else if (currentBMI_formula >= 18.5 && currentBMI_formula <= 24.9) {
            bmiStatement.setText(R.string.normal_weight);
        }else if (currentBMI_formula >= 25 && currentBMI_formula <= 29.9) {
            bmiStatement.setText(R.string.overweight);
        }else if (currentBMI_formula > 30){
            bmiStatement.setText(R.string.obese);
        }

        //BMI result, comparison graph: target vs current BMIs
        graph = findViewById(R.id.bmiGraph);
        if(targetBMI_formula > currentBMI_formula){
            graph.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_increase));
        }else {
            graph.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_decrease));
        }

        back = findViewById(R.id.backButton);
        back.setOnClickListener(v -> onBackPressed());

        next = findViewById(R.id.next);
        next.setOnClickListener(v -> {
            Intent intent = new Intent(BMI.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent =  new Intent(BMI.this, YourHeight.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}