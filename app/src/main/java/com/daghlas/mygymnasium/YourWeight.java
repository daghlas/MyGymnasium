package com.daghlas.mygymnasium;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;

public class YourWeight extends AppCompatActivity {

    NumberPicker weightPicker, unitPicker;
    ImageView back;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_weight);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));

        weightPicker = findViewById(R.id.weightPicker);
        weightPicker.setMinValue(30);
        weightPicker.setMaxValue(180);
        weightPicker.setValue(70);

        unitPicker = findViewById(R.id.unitPicker);
        WeightUnits.initUnits();
        unitPicker.setMaxValue(WeightUnits.getUnitsArrayList().size() -1);
        unitPicker.setMinValue(0);
        unitPicker.setDisplayedValues(WeightUnits.unitNames());

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
                Intent intent = new Intent(YourWeight.this, TargetWeight.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent =  new Intent(YourWeight.this, ChooseTarget.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}