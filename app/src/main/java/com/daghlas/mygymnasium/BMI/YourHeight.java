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
import android.widget.TextView;

import com.daghlas.mygymnasium.R;

public class YourHeight extends AppCompatActivity {

    ImageView back;
    NumberPicker heightPicker, unitPicker;
    Button next;
    SharedPreferences preferences;
    TextView feet, inches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_height);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));

        preferences = getSharedPreferences("VALUES", MODE_PRIVATE);
        heightPicker = findViewById(R.id.weightPicker);
        heightPicker.setMinValue(124);
        heightPicker.setMaxValue(275);
        heightPicker.setValue(150);
        heightPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                preferences.edit().putInt("yourHeight", newVal).apply();

                //conversions & display
                feet = findViewById(R.id.feet);
                inches = findViewById(R.id.inches);
                double value_inches = newVal/2.54;
                int feet1 = (int) (value_inches / 12);
                int inches1 = (int) (value_inches % 12);
                feet.setText(String.valueOf(feet1));
                inches.setText(String.valueOf(inches1));
            }
        });

        unitPicker = findViewById(R.id.unitPicker);
        HeightUnits.initUnits();
        unitPicker.setMaxValue(HeightUnits.getUnitsArrayList().size() -1);
        unitPicker.setMinValue(0);
        unitPicker.setDisplayedValues(HeightUnits.unitNames());

        back = findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YourHeight.this, BMI.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent =  new Intent(YourHeight.this, TargetWeight.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}