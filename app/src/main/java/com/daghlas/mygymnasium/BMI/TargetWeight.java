package com.daghlas.mygymnasium.BMI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;

import com.daghlas.mygymnasium.R;

public class TargetWeight extends AppCompatActivity {

    NumberPicker weightPicker, unitPicker;
    ImageView back;
    Button next;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_weight);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));

        preferences = getSharedPreferences("VALUES", MODE_PRIVATE);
        weightPicker = findViewById(R.id.weightPicker);
        weightPicker.setMinValue(30);
        weightPicker.setMaxValue(180);
        weightPicker.setValue(70);
        weightPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                preferences.edit().putInt("targetWeight", newVal).apply();
            }
        });

        unitPicker = findViewById(R.id.unitPicker);
        WeightUnits.initUnits();
        unitPicker.setMaxValue(WeightUnits.getUnitsArrayList().size() -1);
        unitPicker.setMinValue(0);
        unitPicker.setDisplayedValues(WeightUnits.unitNames());

        back = findViewById(R.id.backButton);
        back.setOnClickListener(v -> onBackPressed());

        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TargetWeight.this, YourHeight.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent =  new Intent(TargetWeight.this, YourWeight.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}