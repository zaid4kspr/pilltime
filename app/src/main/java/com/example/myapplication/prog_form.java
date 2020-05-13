package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.DatePickerDialog;
import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.data.model.ResponseObject;
import com.example.myapplication.ui.DatePickerFragment;
import com.example.myapplication.ui.TimePickerFragment;
import com.example.myapplication.ui.login.LoginActivity;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Calendar;

import butterknife.BindView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class prog_form extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prog_form);


        TextView dateClick = (TextView)findViewById(R.id.dateProgram);

        dateClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();

                datePicker.show(getSupportFragmentManager(), "date picker");

            }
        });
        Button BtnSaveProg = findViewById(R.id.addSaveProg);
        Button BtnCancelProg = findViewById(R.id.addCancelProg);
        BtnSaveProg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i =new Intent(prog_form.this, add_program.class);
                startActivity(i);            }
        });
        BtnCancelProg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i =new Intent(prog_form.this, add_program.class);
                startActivity(i);            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        TextView textView = (TextView) findViewById(R.id.dateProgram);
        textView.setText(currentDateString);
    }










}

