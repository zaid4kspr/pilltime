package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class add_program extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_program);
    }
    public void onClickAddProg(View v){
        Intent i =new Intent(getBaseContext(), prog_form.class);
        startActivity(i);
    }
    public void onClickVisitProg(View v){
        Intent i =new Intent(getBaseContext(), edit_instructions_program.class);
        startActivity(i);
    }
}
