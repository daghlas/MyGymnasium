package com.daghlas.mygymnasium;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {
    public static int SPLASH_SCREEN = 3000;

    CardView card1, card2, card3;
    TextView ash, dev;
    Animation enterLeft, rotateIn, enterBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);

        ash = findViewById(R.id.ash);
        dev = findViewById(R.id.developer);

        enterLeft = AnimationUtils.loadAnimation(this, R.anim.enter_left);
        rotateIn = AnimationUtils.loadAnimation(this, R.anim.rotate_in);
        enterBottom = AnimationUtils.loadAnimation(this, R.anim.enter_bottom);

        ash.startAnimation(enterLeft);
        dev.startAnimation(enterBottom);
        card1.startAnimation(rotateIn);
        card2.startAnimation(rotateIn);
        card3.startAnimation(rotateIn);

        //next activity
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreen.this, ChooseTarget.class);
            startActivity(intent);
            finish();
        }, SPLASH_SCREEN);
    }
}