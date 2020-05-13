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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
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

public class instruction_form extends AppCompatActivity implements DatePickerDialog.OnDateSetListener ,
        TimePickerDialog.OnTimeSetListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction_form);


        TextView timeClick = (TextView) findViewById(R.id.timeInst);
        TextView dateClick = (TextView) findViewById(R.id.dateInst);



        dateClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();

                datePicker.show(getSupportFragmentManager(), "date picker");

            }
        });
        timeClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
        Button BtnSaveInst = findViewById(R.id.addSaveInst);
        Button BtnCancelInst = findViewById(R.id.addCancelInst);
        BtnSaveInst.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i =new Intent(instruction_form.this, edit_instructions_program.class);
                startActivity(i);            }
        });
        BtnCancelInst.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i =new Intent(instruction_form.this, edit_instructions_program.class);
                startActivity(i);            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.spinnerMed);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Med,android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        TextView textView = (TextView) findViewById(R.id.dateInst);
        textView.setText(currentDateString);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView textView = (TextView) findViewById(R.id.timeInst);
        textView.setText( hourOfDay + " : " + minute);
    }











}

