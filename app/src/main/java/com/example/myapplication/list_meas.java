package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.listOfTemp.temp;

public class list_meas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meas);
    }
    public void onClickAddMeas(View v) {
        Intent i =new Intent(getBaseContext(), meas_select.class);
        startActivity(i);            }

    public void onClickGoToTemp(View v) {
        Intent i =new Intent(getBaseContext(), temp.class);
        startActivity(i);            }
}
