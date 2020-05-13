package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class edit_instructions_program extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_instructions_program);
    }
    public void onClickAddInstr(View v){
        Intent i =new Intent(getBaseContext(), instruction_form.class);
        startActivity(i);
    }
}
