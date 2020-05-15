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
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.data.model.ResponseObject;
import com.example.myapplication.data.model.ProgrammeModel;
import com.example.myapplication.ui.DatePickerFragment;
import com.example.myapplication.ui.TimePickerFragment;
import com.example.myapplication.ui.login.LoginActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class prog_form extends AppCompatActivity implements DatePickerDialog.OnDateSetListener   {

    @BindView(R.id.maladie) TextInputLayout maladie;
    @BindView(R.id.addDayss) TextInputLayout addDayss;
    @BindView(R.id.dateProgramDebut) TextView dateProgramDebut;

    String date;
    String time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prog_form);


        TextView dateClick = (TextView)findViewById(R.id.dateProgramDebut);
        dateClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();

                datePicker.show(getSupportFragmentManager(), "date picker");

            }
        });

        Button BtnSaveProg = findViewById(R.id.addSaveProg);

        BtnSaveProg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addProgramme();
//                Intent i =new Intent(prog_form.this, add_program.class);
  //              startActivity(i);
            }
        });
            Button BtnCancelProg = findViewById(R.id.addCancelProg);
        BtnCancelProg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i =new Intent(prog_form.this, add_program.class);
                startActivity(i);            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)  {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        TextInputLayout plusDays =findViewById(R.id.addDayss);

        c.add(c.DATE,Integer.parseInt(plusDays.getEditText().getText().toString()));

        TextView textView = (TextView) findViewById(R.id.dateProgramDebut);
        textView.setText(currentDateString);
        String currentDateStringF = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());




    }
    public void addProgramme() {

        String Maladie = this.maladie.getEditText().getText().toString();
        int nbjours =Integer.parseInt(this.addDayss.getEditText().getText().toString());
       String dateDebut = this.dateProgramDebut.getText().toString();
       // String dateFin =this.dateProgramDebut.getEditText().getText().toString();
        SharedPreferences preferences = getSharedPreferences("myprefs", MODE_PRIVATE);
        String user = preferences.getString("id", "");

        ProgrammeModel p = new ProgrammeModel(dateDebut,dateDebut, nbjours,Maladie,user);


        Call<ResponseBody> call = RetrofitClient.getInstance().getApi().addProgramme(p);
        Log.d("date",p.getDateDebut());
        Log.d("datef",p.getDateFin());
        Log.d("jours", String.valueOf(p.getDuree()));
        Log.d("malad",p.getMaladie());

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                if (response.isSuccessful()) {
                    // RouteToHome();
                  Toast.makeText(prog_form.this, "added with sucess", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(prog_form.this, "failed add", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(prog_form.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }












}
