package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.OnClick;

public class takeActionTemp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_action_temp);
    }
    public void sendSms(View v) {
        Intent i =new Intent(getBaseContext(), SMS.class);
        startActivity(i);

    }
    public void doctorsMap(View v) {
        Intent i =new Intent(getBaseContext(), myposition.class);
        startActivity(i);

    }

}
