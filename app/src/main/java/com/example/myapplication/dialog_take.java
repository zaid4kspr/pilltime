package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.util.Log;
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

import com.example.myapplication.alerteReceiverPack.AlertReceiver;
import com.example.myapplication.data.model.AddPriseAndUpdateProgramModel;
import com.example.myapplication.data.model.PriseModel;
import com.example.myapplication.data.model.PrisesResponse;
import com.example.myapplication.data.model.ProgrammeModel;
import com.example.myapplication.data.model.prisePostModel;
import com.example.myapplication.ui.DatePickerFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import okhttp3.ResponseBody;
import okhttp3.Route;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dialog_take extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {


    String userId;
    ArrayList<ProgrammeModel> programmeList;
    ArrayList<String> t = new ArrayList<String>();
    ProgrammeModel myChoosenProgram;
    ArrayList<String> maladie = new ArrayList<String>();

    Spinner spinner1;
    Spinner spinner2;
    Date dateDebutJava;

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    ArrayAdapter<String> adapter;
    TimePickerDialog timePickerDialog;
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

    @BindView(R.id.qte1)
    TextInputLayout qte1;
    @BindView(R.id.qte2)
    TextInputLayout qte2;
    @BindView(R.id.qte3)
    TextInputLayout qte3;

    String name;
    String id;
    String dateDebut;
    String Duree;
    String program;
    String user;
    Integer timeInputNumber;

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
        dateDebutInput.getEditText().setText(dateDebut.substring(0, 10));
        duree.getEditText().setText(Duree);

        SharedPreferences preferences = getSharedPreferences("myprefs", MODE_PRIVATE);
        userId = preferences.getString("id", "");
        getListOfProgrammes();
        takes2.setVisibility(View.GONE);
        takes3.setVisibility(View.GONE);

        t.add("0");
        t.add("0");
        t.add("0");
        // Get Current Time
        Calendar c = Calendar.getInstance();
        Integer mHour = c.get(Calendar.HOUR_OF_DAY);
        Integer mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        Calendar c1 = Calendar.getInstance();
                        c1.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        c1.set(Calendar.MINUTE, minute);
                        c1.set(Calendar.SECOND, 0);
                       //   startAlarm(c1);

                        String h = String.format("%02d", hourOfDay);
                        String m = String.format("%02d", minute);

                        if (timeInputNumber == 0) {
                            t.set(0, h + ":" + m);
                            time1Input.setText(t.get(0));

                        }
                        if (timeInputNumber == 1) {
                            t.set(1, h + ":" + m);
                            time2Input.setText(t.get(1));

                        }
                        if (timeInputNumber == 2) {
                            t.set(2, h + ":" + m);
                            time3Input.setText(t.get(2));

                        }
                    }
                }, mHour, mMinute, true);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        //   Toast.makeText(parent.getContext(), text + "  " + parent.getId(), Toast.LENGTH_SHORT).show();
        switch (parent.getId()) {
            case R.id.spinner:
                //   Toast.makeText(parent.getContext(), "1-" + text, Toast.LENGTH_SHORT).show();


                myChoosenProgram = programmeList.get(position);


                break;
            case R.id.remindersTimes:
                //   Toast.makeText(parent.getContext(), "2-" + text, Toast.LENGTH_SHORT).show();
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
        myChoosenProgram.setDateDebut(currentDateString);

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


        for (Integer i = 0; i < programmeList.size(); i++) {
            if (program.equals(programmeList.get(i).getId())) ;
            {
                //  Toast.makeText(this, "zzzzzzzzzz" + program, Toast.LENGTH_SHORT).show();
                myChoosenProgram = programmeList.get(i);
                spinner1.setSelection(i);
            }


        }


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


        //  timePickerDialog.dismiss();
        timePickerDialog.show();

    }


    @OnClick(R.id.time1)
    public void openT1() {
        timeInputNumber = 0;
        openTimePicker(0);
    }


    @OnFocusChange(R.id.time1)
    public void openT(boolean focused) {
        if (focused) {
            timeInputNumber = 0;
            openTimePicker(0);
        }
    }


    @OnClick(R.id.time2)
    public void openT2() {
        timeInputNumber = 1;
        openTimePicker(1);
    }


    @OnFocusChange(R.id.time2)
    public void openT2B(boolean focused) {
        if (focused) {
            timeInputNumber = 1;
            openTimePicker(1);
        }
    }


    @OnClick(R.id.time3)
    public void openT3() {
        timeInputNumber = 2;
        openTimePicker(2);
    }


    @OnFocusChange(R.id.time3)
    public void openT3B(boolean focused) {
        if (focused) {
            timeInputNumber = 2;
            openTimePicker(2);
        }

    }

    public void patchMyInputs() {

    }

    @OnClick(R.id.addBtn)
    public void addPriseAndUpdateMed() {


        ArrayList<prisePostModel> priseArr = new ArrayList<prisePostModel>();

        myChoosenProgram.setMaladie(nameInput.getEditText().getText().toString());
        myChoosenProgram.setDuree(Integer.parseInt(duree.getEditText().getText().toString()));
        Log.d("myChoosenProgram", myChoosenProgram.getMaladie());
        ArrayList<Integer> qtes = new ArrayList<Integer>();

        Integer qte = 0;
        for (Integer i = 0; i < timeInputNumber + 1; i++) {

            if (i == 0) {
                qte = Integer.parseInt(qte1.getEditText().getText().toString());
            }
            if (i == 1) {
                qte = Integer.parseInt(qte2.getEditText().getText().toString());
            }
            if (i == 2) {
                qte = Integer.parseInt(qte3.getEditText().getText().toString());
            }
            prisePostModel temp = new prisePostModel("", "", t.get(i), qte, id, userId);
            priseArr.add(temp);

        }

        AddPriseAndUpdateProgramModel p = new AddPriseAndUpdateProgramModel(myChoosenProgram, priseArr);

        Call<ArrayList<PrisesResponse>> call = RetrofitClient.getInstance().getApi().prisev2(
                p
        );

        call.enqueue(new Callback<ArrayList<PrisesResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<PrisesResponse>> call, Response<ArrayList<PrisesResponse>> response) {


                if (response.isSuccessful()) {


                    for (Integer i=0; i<1;i++){

                        Log.d("priseCourant", "onResponse: " + response.body().get(0).getHeure());

                        Integer hour = Integer.parseInt(response.body().get(i).getHeure().substring(0, 2));
                        Integer minute = Integer.parseInt(response.body().get(i).getHeure().substring(3, 5));

                        Integer year = Integer.parseInt(response.body().get(i).getDate().substring(0, 4));
                        Integer month = Integer.parseInt(response.body().get(i).getDate().substring(5, 7))-1;
                        Integer day = Integer.parseInt(response.body().get(i).getDate().substring(8, 10));


                        Log.d("hour", "onResponse: " + hour);
                        Log.d("minute", "onResponse: " + minute);
                        Log.d("year", "onResponse: " + year);
                        Log.d("month", "onResponse: " + month);
                        Log.d("day", "onResponse: " + day);

                        Calendar myAlarmDate = Calendar.getInstance();
                        myAlarmDate.set(Calendar.HOUR_OF_DAY, hour);
                        myAlarmDate.set(Calendar.MINUTE, minute);
                        myAlarmDate.set(Calendar.DAY_OF_MONTH, day);
                        myAlarmDate.set(Calendar.MONTH, month);
                        myAlarmDate.set(Calendar.YEAR, year);
                        myAlarmDate.set(Calendar.SECOND, 0);

                        Log.d("alarm", "onResponse: "+myAlarmDate.toString());
                        startAlarm(myAlarmDate,i);

                    }


                    RouteToHome();

                    Toast.makeText(dialog_take.this, "added with sucess", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<ArrayList<PrisesResponse>> call, Throwable t) {
                Toast.makeText(dialog_take.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    void RouteToHome() {
        Intent i = new Intent(dialog_take.this, MainActivity.class);
        startActivity(i);
    }

    private void startAlarm(Calendar c,Integer id) {
        Log.d("zzzz","alooooooo");
        Toast.makeText(this, "zzzz", Toast.LENGTH_SHORT).show();
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, id, intent, PendingIntent.FLAG_ONE_SHOT);
        if (c.before(Calendar.getInstance())) {
            Log.d("zzzz","gdim");

            c.add(Calendar.DATE, 1);
        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        Log.d("saye","saye");

    }

    private void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        alarmManager.cancel(pendingIntent);
        // mTextView.setText("Alarm canceled");
    }


}



