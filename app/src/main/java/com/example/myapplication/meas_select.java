package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class meas_select extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meas_select);

    }
    public void onClickSelectedMeas(View v) {
        Intent i =new Intent(getBaseContext(), list_meas.class);
        startActivity(i);            }
}
