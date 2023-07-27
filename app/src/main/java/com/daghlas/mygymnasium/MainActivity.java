package com.daghlas.mygymnasium;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    TextView greeting_tag, showGlass, startToday, glassesRemaining, glassesDrunk;
    ImageView greeting_image;
    BottomNavigationView navigationView;
    Button create, reset, setGlass;
    LinearLayout createReminder, waterProgress;
    ScrollView scrollView;
    View overlay;
    CardView cardView1, minusGlass, addGlass;
    ProgressBar progressBar;
    int currentValue = 0;
    int drinking = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue));

        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFFFF'>Ashley's GYM App</font>"));
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
        }

        greeting_tag = findViewById(R.id.greeting);
        greeting_image = findViewById(R.id.greeting_image);

        //
        startToday = findViewById(R.id.textView3);
        waterProgress = findViewById(R.id.waterProgress);
        glassesRemaining = findViewById(R.id.remaining);
        glassesDrunk = findViewById(R.id.drunk);

        //Greeting tag - set according to time of day
        Calendar c = Calendar.getInstance();
        int time = c.get(Calendar.HOUR_OF_DAY);

        if (time < 12) {
            greeting_tag.setText(R.string.good_morning);
            greeting_image.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_sunrise));
        } else if (time < 16) {
            greeting_tag.setText(R.string.good_afternoon);
            greeting_image.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_good_afternoon));
        } else if (time < 21) {
            greeting_tag.setText(R.string.good_evening);
            greeting_image.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_good_evening));
        } else {
            greeting_tag.setText(R.string.good_night);
            greeting_image.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_good_night));
        }

        create = findViewById(R.id.create);
        reset = findViewById(R.id.reset);
        createReminder = findViewById(R.id.windowLayout);
        scrollView = findViewById(R.id.scrollView1);
        navigationView = findViewById(R.id.bottom_nav);
        overlay = findViewById(R.id.overlay);
        cardView1 = findViewById(R.id.cardView1);
        progressBar = findViewById(R.id.progress);

        reset.setVisibility(View.INVISIBLE);
        progressBar.setProgress(0);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (createReminder.getVisibility() == View.VISIBLE) {
                    createReminder.setVisibility(View.GONE);
                    overlay.setVisibility(View.GONE);
                    navigationView.setVisibility(View.VISIBLE);
                } else if(createReminder.getVisibility() == View.GONE && currentValue > 0){
                    if(drinking < currentValue){
                        drinking ++;
                        glassesDrunk.setText(String.valueOf(drinking));
                        //progressBar setting
                        int numerator = Integer.parseInt(glassesDrunk.getText().toString().trim());;
                        int denominator = Integer.parseInt(glassesRemaining.getText().toString().trim());
                        progressBar.setMax(denominator);
                        progressBar.setProgress(numerator);

                    }else if(drinking == currentValue){
                        glassesDrunk.setText(String.valueOf(drinking));
                        Toast.makeText(MainActivity.this, "Well Done! Milestone complete", Toast.LENGTH_LONG).show();
                        create.setEnabled(false);
                        create.setText(R.string.completed);
                    }
                } else{
                    createReminder.setVisibility(View.VISIBLE);
                    overlay.setVisibility(View.VISIBLE);
                    navigationView.setVisibility(View.GONE);
                    scrollView.setEnabled(false);
                    scrollView.setVerticalScrollBarEnabled(false);
                }
            }
        });

        //add and remove glass amounts
        addGlass = findViewById(R.id.addGlass);
        minusGlass = findViewById(R.id.minusGlass);
        showGlass = findViewById(R.id.showGlass);
        setGlass = findViewById(R.id.setGlass);
        minusGlass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentValue > 0) {
                    currentValue--;
                    showGlass.setText(String.valueOf(currentValue));
                }
            }
        });
        addGlass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentValue < 10) {
                    currentValue++;
                    showGlass.setText(String.valueOf(currentValue));
                }
            }
        });
        setGlass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentValue == 0){
                    Toast.makeText(MainActivity.this, "Add one or more glasses", Toast.LENGTH_SHORT).show();
                }else{
                    createReminder.setVisibility(View.GONE);
                    overlay.setVisibility(View.GONE);
                    navigationView.setVisibility(View.VISIBLE);
                    startToday.setVisibility(View.INVISIBLE);
                    waterProgress.setVisibility(View.VISIBLE);
                    glassesRemaining.setText(String.valueOf(currentValue));
                    create.setText(R.string.drink);
                    reset.setVisibility(View.VISIBLE);
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drinking = 0;
                create.setEnabled(true);
                createReminder.setVisibility(View.VISIBLE);
                overlay.setVisibility(View.VISIBLE);
                navigationView.setVisibility(View.GONE);
                glassesDrunk.setText("0");
                progressBar.setProgress(0);
                create.setText(R.string.drink);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (createReminder.getVisibility() == View.VISIBLE) {
            createReminder.setVisibility(View.GONE);
            overlay.setVisibility(View.GONE);
            navigationView.setVisibility(View.VISIBLE);

        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}