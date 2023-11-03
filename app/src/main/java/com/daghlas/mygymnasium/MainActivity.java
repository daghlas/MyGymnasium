package com.daghlas.mygymnasium;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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

import com.daghlas.mygymnasium.Routines.ButtLegRoutines;
import com.daghlas.mygymnasium.Routines.FullBodyRoutines;
import com.daghlas.mygymnasium.Routines.YogaRoutines;
import com.daghlas.mygymnasium.Workouts.Yoga;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    TextView greeting_tag, showGlass, startToday, glassesRemaining, glassesDrunk, dayOfWeek;
    TextView morningMeal1, morningMeal2, morningMeal3;
    TextView afternoonMeal1, afternoonMeal2, afternoonMeal3;
    TextView eveningMeal1, eveningMeal2, eveningMeal3;
    ImageView greeting_image;
    BottomNavigationView navigationView;
    Button create, reset, setGlass;
    LinearLayout createReminder, waterProgress;
    ScrollView scrollView;
    View overlay;
    CardView cardView1, minusGlass, addGlass, fullBodyRoutine, buttLegsRoutine, editMealPlan,
            absRoutine, yogaRoutine;
    ProgressBar progressBar;
    int currentValue = 0;
    int drinking = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue));

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFFFF'>k.d_kai's GYM App</font>"));
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
        }

        greeting_tag = findViewById(R.id.greeting);
        greeting_image = findViewById(R.id.greeting_image);

        //
        startToday = findViewById(R.id.textView3);
        waterProgress = findViewById(R.id.waterProgress);
        glassesRemaining = findViewById(R.id.remaining);
        glassesDrunk = findViewById(R.id.drunk);
        dayOfWeek = findViewById(R.id.dayOfWeek);

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

        morningMeal1 = findViewById(R.id.morning1);
        morningMeal2 = findViewById(R.id.morning2);
        morningMeal3 = findViewById(R.id.morning3);

        afternoonMeal1 = findViewById(R.id.afternoon1);
        afternoonMeal2 = findViewById(R.id.afternoon2);
        afternoonMeal3 = findViewById(R.id.afternoon3);

        eveningMeal1 = findViewById(R.id.evening1);
        eveningMeal2 = findViewById(R.id.evening2);
        eveningMeal3 = findViewById(R.id.evening3);

        //setting current day of the week to display on meal plan layout
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
        String currentDayOfWeek = dateFormat.format(c.getTime()).toUpperCase();
        dayOfWeek.setText(currentDayOfWeek);
        //method to set meal plan layout
        mealPlanLayout();

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
                    fullBodyRoutine.setEnabled(true);
                    buttLegsRoutine.setEnabled(true);
                    yogaRoutine.setEnabled(true);
                } else if (createReminder.getVisibility() == View.GONE && currentValue > 0) {
                    if (drinking < currentValue) {
                        drinking++;
                        glassesDrunk.setText(String.valueOf(drinking));
                        //progressBar setting
                        int numerator = Integer.parseInt(glassesDrunk.getText().toString().trim());
                        int denominator = Integer.parseInt(glassesRemaining.getText().toString().trim());
                        progressBar.setMax(denominator);
                        progressBar.setProgress(numerator);
                        fullBodyRoutine.setEnabled(true);
                        buttLegsRoutine.setEnabled(true);
                        yogaRoutine.setEnabled(true);
                    } else if (drinking == currentValue) {
                        glassesDrunk.setText(String.valueOf(drinking));
                        Toast.makeText(MainActivity.this, "Well Done! Milestone complete", Toast.LENGTH_LONG).show();
                        create.setEnabled(false);
                        create.setText(R.string.completed);
                    }
                } else {
                    createReminder.setVisibility(View.VISIBLE);
                    overlay.setVisibility(View.VISIBLE);
                    navigationView.setVisibility(View.GONE);
                    scrollView.setEnabled(false);
                    scrollView.setVerticalScrollBarEnabled(false);
                    fullBodyRoutine.setEnabled(false);
                    buttLegsRoutine.setEnabled(false);
                    yogaRoutine.setEnabled(false);
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
                if (currentValue == 0) {
                    Toast.makeText(MainActivity.this, "Add one or more glasses", Toast.LENGTH_SHORT).show();
                } else {
                    createReminder.setVisibility(View.GONE);
                    overlay.setVisibility(View.GONE);
                    navigationView.setVisibility(View.VISIBLE);
                    startToday.setVisibility(View.INVISIBLE);
                    waterProgress.setVisibility(View.VISIBLE);
                    glassesRemaining.setText(String.valueOf(currentValue));
                    create.setText(R.string.drink);
                    reset.setVisibility(View.VISIBLE);
                    fullBodyRoutine.setEnabled(true);
                    buttLegsRoutine.setEnabled(true);
                    yogaRoutine.setEnabled(true);
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
                fullBodyRoutine.setEnabled(false);
                buttLegsRoutine.setEnabled(false);
                yogaRoutine.setEnabled(false);
            }
        });

        fullBodyRoutine = findViewById(R.id.cardView2);
        fullBodyRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FullBodyRoutines.class);
                startActivity(intent);
                //finish();
            }
        });

        buttLegsRoutine = findViewById(R.id.cardView3);
        buttLegsRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ButtLegRoutines.class);
                startActivity(intent);
                //finish();
            }
        });

        yogaRoutine = findViewById(R.id.cardView5);
        yogaRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, YogaRoutines.class);
                startActivity(intent);
                //finish();
            }
        });

        editMealPlan = findViewById(R.id.editMealPlan);
        editMealPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Edit Meal Plan");
                builder.setMessage("Customise this meal plan to match your own desired meal preferences " +
                        "for each day.");
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setCancelable(false);
                builder.setPositiveButton("PROCEED", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //method to set and save new meal plan
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this, "Coming soon", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (createReminder.getVisibility() == View.VISIBLE) {
            createReminder.setVisibility(View.GONE);
            overlay.setVisibility(View.GONE);
            navigationView.setVisibility(View.VISIBLE);
            fullBodyRoutine.setEnabled(true);
            buttLegsRoutine.setEnabled(true);
            yogaRoutine.setEnabled(true);
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

    @SuppressLint("SetTextI18n")
    public void mealPlanLayout() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
        String currentDayOfWeek = dateFormat.format(c.getTime()).toUpperCase();
        //dayOfWeek.setText(currentDayOfWeek);

        switch (currentDayOfWeek) {
            case "MONDAY":
                morningMeal1.setText("Porridge");
                morningMeal2.setText("Mandazi");
                morningMeal3.setText("Banana");

                afternoonMeal1.setText("Ugali");
                afternoonMeal2.setText("Vegetables");
                afternoonMeal3.setText("Beef");

                eveningMeal1.setText("Chapati");
                eveningMeal2.setText("Beans");
                eveningMeal3.setText("Fruit");
                break;
            case "TUESDAY":
                morningMeal1.setText("Milk");
                morningMeal2.setText("Bread");
                morningMeal3.setText("Apple");

                afternoonMeal1.setText("Rice");
                afternoonMeal2.setText("Peas");
                afternoonMeal3.setText("Spinach");

                eveningMeal1.setText("Ugali");
                eveningMeal2.setText("Kales");
                eveningMeal3.setText("Liver");
                break;
            case "WEDNESDAY":
                morningMeal1.setText("Coffee");
                morningMeal2.setText("Chapati");
                morningMeal3.setText("Melon");

                afternoonMeal1.setText("Ugali");
                afternoonMeal2.setText("Vegetables");
                afternoonMeal3.setText("Fish");

                eveningMeal1.setText("Chapati");
                eveningMeal2.setText("Chicken");
                eveningMeal3.setText("Fruit");
                break;
            case "THURSDAY":
                morningMeal1.setText("Milk");
                morningMeal2.setText("Sausages");
                morningMeal3.setText("Chapati");

                afternoonMeal1.setText("Rice");
                afternoonMeal2.setText("Vegetables");
                afternoonMeal3.setText("Beef");

                eveningMeal1.setText("Potatoes");
                eveningMeal2.setText("Rice");
                eveningMeal3.setText("Vegetables");
                break;
            case "FRIDAY":
                morningMeal1.setText("Porridge");
                morningMeal2.setText("Mandazi");
                morningMeal3.setText("Pineapples");

                afternoonMeal1.setText("Ugali");
                afternoonMeal2.setText("Vegetables");
                afternoonMeal3.setText("Beef");

                eveningMeal1.setText("Chapati");
                eveningMeal2.setText("Green grams");
                eveningMeal3.setText("Fruit");
                break;
            case "SATURDAY":
            case "SUNDAY":
                morningMeal1.setText("Porridge");
                morningMeal2.setText("Sweet potatoes");
                morningMeal3.setText("Fruit");

                afternoonMeal1.setText("Ugali");
                afternoonMeal2.setText("Spinach");
                afternoonMeal3.setText("Pork");

                eveningMeal1.setText("Potatoes");
                eveningMeal2.setText("Rice");
                eveningMeal3.setText("Fruit");
                break;
        }
    }

}