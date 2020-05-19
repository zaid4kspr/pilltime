package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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

import com.example.myapplication.data.model.ProgrammeModel;
import com.example.myapplication.ui.DatePickerFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dialog_take extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {




    String userId;
    ArrayList<ProgrammeModel> programmeList;
    ArrayList<String> maladie = new ArrayList<String>();

    Spinner spinner1;
    Spinner spinner2;
    Date dateDebutJava;
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    ArrayAdapter<String> adapter;

    @BindView(R.id.takes1)
    LinearLayout takes1;
    @BindView(R.id.takes2)
    LinearLayout takes2;
    @BindView(R.id.takes3)
    LinearLayout takes3;
    @BindView(R.id.time1)
    TextInputEditText time1Input;
    @BindView(R.id.time2)
    TextInputEditText time2Input;
    @BindView(R.id.time3)
    TextInputEditText time3Input;

    @BindView(R.id.name)
    TextInputLayout nameInput;

    @BindView(R.id.dateDebut)
    TextInputLayout dateDebutInput;

    @BindView(R.id.duree_med_form)
    TextInputLayout duree;

    String name;
    String id;
    String dateDebut;
    String Duree;
    String program;
    String user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_take);

        ButterKnife.bind(this);
        id = (getIntent().getStringExtra("id"));
        name = (getIntent().getStringExtra("name"));
        dateDebut = (getIntent().getStringExtra("dateDebut"));
        Duree = (getIntent().getStringExtra("Duree"));
        program = (getIntent().getStringExtra("program"));

        nameInput.getEditText().setText(name);
        dateDebutInput.getEditText().setText(dateDebut.substring(0,10));
        duree.getEditText().setText(Duree);

        SharedPreferences preferences = getSharedPreferences("myprefs", MODE_PRIVATE);
        userId = preferences.getString("id", "");
        getListOfProgrammes();
        takes2.setVisibility(View.GONE);
        takes3.setVisibility(View.GONE);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text + "  " + parent.getId(), Toast.LENGTH_SHORT).show();
        switch (parent.getId()) {
            case R.id.spinner:
                Toast.makeText(parent.getContext(), "1-" + text, Toast.LENGTH_SHORT).show();

                break;
            case R.id.remindersTimes:
                Toast.makeText(parent.getContext(), "2-" + text, Toast.LENGTH_SHORT).show();
                takes1.setVisibility(View.VISIBLE);

                if (position == 0) {
                    takes1.setVisibility(View.VISIBLE);
                    takes2.setVisibility(View.GONE);
                    takes3.setVisibility(View.GONE);

                }
                if (position == 1) {
                    takes1.setVisibility(View.VISIBLE);
                    takes2.setVisibility(View.VISIBLE);
                    takes3.setVisibility(View.GONE);
                }
                if (position == 2) {
                    takes1.setVisibility(View.VISIBLE);
                    takes2.setVisibility(View.VISIBLE);
                    takes3.setVisibility(View.VISIBLE);
                }


                break;
        }
    }


    @OnFocusChange(R.id.dateDebutInput)
    public void openCalender(View view, boolean hasFocus) {
        if (hasFocus) {
            DialogFragment datePicker = new DatePickerFragment();
            datePicker.show(getSupportFragmentManager(), "date pickerr");
        }

    }

    @OnClick(R.id.dateDebutInput)
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
        dateDebutInput.getEditText().setText(currentDateString);


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void getListOfProgrammes() {

        String userQueryparam = "{\"user\":\"" + userId + "\" }";

        Call<ArrayList<ProgrammeModel>> call = RetrofitClient.getInstance().getApi().getProgramme(
                userQueryparam
        );
        call.enqueue(new Callback<ArrayList<ProgrammeModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProgrammeModel>> call, Response<ArrayList<ProgrammeModel>> response) {
                if (response.isSuccessful()) {

                    System.out.println(response.code());
                    programmeList = response.body();

                    maladie.clear();

                    for (Integer i = 0; i < programmeList.size(); i++) {
                        maladie.add(programmeList.get(i).getMaladie());
                    }


                } else {
                    System.out.println("Something went wrong!");
                }

                fillSpinnerItems1();
                fillSpinnerItems2();

            }

            @Override
            public void onFailure(Call<ArrayList<ProgrammeModel>> call, Throwable t) {
                System.out.println("Something went wrong!");

            }
        });


    }

    public void fillSpinnerItems1() {
        spinner1 = (Spinner) findViewById(R.id.spinner);
        spinner1.setOnItemSelectedListener(this);

        // Create an ArrayAdapter using the string array and a default spinner layout
        adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, maladie);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner1.setAdapter(adapter);

        int pos = adapter.getPosition(program);
        spinner1.setSelection(2);


    }

    public void fillSpinnerItems2() {
        spinner2 = (Spinner) findViewById(R.id.remindersTimes);

        ArrayList<String> times = new ArrayList<String>();

        times.add("Une fois par jour");
        times.add("Deux fois par jour");
        times.add("Trois fois par jour");
        spinner2.setOnItemSelectedListener(this);

        // Create an ArrayAdapter using the string array and a default spinner layout
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, times);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner2.setAdapter(adapter);
    }

    public void openTimePicker(Integer timeInputNumber) {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        Integer mHour = c.get(Calendar.HOUR_OF_DAY);
        Integer mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        String h = String.format("%02d", hourOfDay);
                        String m = String.format("%02d", minute);

                        if (timeInputNumber == 0) {
                            String t = h + ":" + m;
                            time1Input.setText(t);

                        }
                        if (timeInputNumber == 1) {
                            String t2 = h + ":" + m;
                            time2Input.setText(t2);

                        }
                        if (timeInputNumber == 2) {
                            String t3 = h + ":" + m;
                            time3Input.setText(t3);

                        }

                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();

    }


    @OnClick(R.id.time1)
    public void openT1() {
        openTimePicker(0);
    }


    @OnFocusChange(R.id.time1)
    public void openT() {
        openTimePicker(0);
    }


    @OnClick(R.id.time2)
    public void openT2() {
        openTimePicker(1);
    }


    @OnFocusChange(R.id.time2)
    public void openT2B() {
        openTimePicker(1);
    }


    @OnClick(R.id.time3)
    public void openT3() {
        openTimePicker(2);
    }


    @OnFocusChange(R.id.time3)
    public void openT3B() {
        openTimePicker(2);
    }

    public void patchMyInputs() {

    }


}

