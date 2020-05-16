package com.example.myapplication;
import com.example.myapplication.listOfProgramms.listOfPrograms;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.DatePicker;
import android.widget.TimePicker;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.data.model.MedicamentModel;
import com.example.myapplication.data.model.PriseModel;
import com.example.myapplication.data.model.ProgrammeModel;
import com.example.myapplication.data.model.TemperatureModel;
import com.example.myapplication.listOfPriseHomePage.listMedsAdapter;
import com.example.myapplication.ui.DatePickerFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class prog_form extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @BindView(R.id.dateDebutP)
    TextInputLayout dateDebut;
    @BindView(R.id.nameP)
    TextInputLayout medName;
    @BindView(R.id.duree_prog_form)
    TextInputLayout duree_med_form;
    Date dateDebutJava;
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prog_form);

        SharedPreferences preferences = getSharedPreferences("myprefs", MODE_PRIVATE);
        userId = preferences.getString("id", "");

        ButterKnife.bind(this);



    }

    @OnFocusChange(R.id.dateDebutInputP)
    public void openCalender(View view, boolean hasFocus) {
        if (hasFocus) {
            DialogFragment datePicker = new DatePickerFragment();
            datePicker.show(getSupportFragmentManager(), "date pickerr");
        }

    }

    @OnClick(R.id.dateDebutInputP)
    public void openCalenderr(View view) {

        DialogFragment datePicker = new DatePickerFragment();
        datePicker.show(getSupportFragmentManager(), "date pickerr");

    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


        dateDebutJava = c.getTime();

        String currentDateString = dateFormat.format(c.getTime());
        dateDebut.getEditText().setText(currentDateString);


    }

    @OnClick(R.id.addCancelProg)
     void onClickCancelProg(View v){
        Intent i =new Intent(getBaseContext(), listOfPrograms.class);
        startActivity(i);
    }



    @OnClick(R.id.addSaveProg)
    public void addProgramme() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Integer duree = Integer.parseInt(duree_med_form.getEditText().getText().toString()) ;
        String name = medName.getEditText().getText().toString() ;


        Calendar c = Calendar.getInstance();
        c.setTime(dateDebutJava);
        c.add(Calendar.DATE, duree);

        //date fin et debut of programm
        String dateFin = dateFormat.format(c.getTime());
        String datedebutLocal =  dateDebut.getEditText().getText().toString();


        SharedPreferences preferences = getSharedPreferences("myprefs", MODE_PRIVATE);

        ProgrammeModel t = new ProgrammeModel(datedebutLocal,dateFin,duree,name,userId);

        Call<ResponseBody> call = RetrofitClient.getInstance().getApi().addProgramme(t);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                if (response.isSuccessful()) {
                    // RouteToHome();
                    Toast.makeText(prog_form.this, "added with sucess", Toast.LENGTH_SHORT).show();
                    routeToProgList();

                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(prog_form.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    void routeToProgList(){
        Intent i =new Intent(getBaseContext(), listOfPrograms.class);
        startActivity(i);
    }





}

