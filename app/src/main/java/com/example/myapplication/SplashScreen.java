package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.ui.login.LoginActivity;

public class SplashScreen extends AppCompatActivity {
    private static int splashTime = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences preferences = getSharedPreferences("myprefs", MODE_PRIVATE);

        String id = preferences.getString("id", "");


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i;
                if (! (id.length() > 0) && (id != null)){
                  i  = new Intent(SplashScreen.this, LoginActivity.class);

                }else{
                    i = new Intent(SplashScreen.this, MainActivity.class);

                }

                startActivity(i);
                finish();
            }
        }, splashTime);
    }

    public void run() {
        Intent i = new Intent(SplashScreen.this, register.class);
        startActivity(i);
        finish();
    }


}