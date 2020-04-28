package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.ui.login.LoginActivity;

public class SplashScreen extends AppCompatActivity {
    private static int splashTime=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i =new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        },splashTime);





    }

    public void run(){
        Intent i =new Intent(SplashScreen.this,register.class);
        startActivity(i);
        finish();
    }




}
