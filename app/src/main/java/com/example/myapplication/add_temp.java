package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.app.DatePickerDialog;

import androidx.fragment.app.DialogFragment;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.data.model.TemperatureModel;
import com.example.myapplication.listOfTemp.temp;
import com.example.myapplication.ui.DatePickerFragment;
import com.example.myapplication.ui.TimePickerFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class add_temp extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    AlertDialog.Builder builder;

    @BindView(R.id.notesTemp)
    TextInputLayout notesTemp;
    @BindView(R.id.duree_med_form) TextInputLayout temperature;
    String date;
    Float checkTemp;

    String time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_temp);

        ButterKnife.bind(this);

        TextView timeClick = (TextView) findViewById(R.id.timeP);
        TextView dateClick = (TextView) findViewById(R.id.date);


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
        Button BtnSave = findViewById(R.id.addSaveTemp);
        Button BtnCancel = findViewById(R.id.addCancelTemp);
        BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    addTemperature();


            }
        });
        BtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(add_temp.this, temp.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        TextView textView = (TextView) findViewById(R.id.date);
        textView.setText(currentDateString);
        //2020-05-12T00:48:49.552+00:00
      date=""+year+"-"+ String.format("%02d", month)+"-"+ String.format("%02d", dayOfMonth);
    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView textView = (TextView) findViewById(R.id.timeP);
        textView.setText(String.format("%02d", hourOfDay) + " : " + String.format("%02d", minute));
        time=  String.format("%02d", hourOfDay)  + ":" + String.format("%02d", minute);
    }


    public void addTemperature() {

          String notesTemp = this.notesTemp.getEditText().getText().toString();
        Float temperature =Float.parseFloat(this.temperature.getEditText().getText().toString()) ;

        String ref_p = "5eb97f0c2cb46e3b6895c672";
        SharedPreferences preferences = getSharedPreferences("myprefs", MODE_PRIVATE);
        String user = preferences.getString("id", "");

        TemperatureModel t = new TemperatureModel(temperature, date+"T"+time, ref_p, user,notesTemp);
        if ( Float.compare(temperature, 40f) >0) {
            builder = new AlertDialog.Builder(this);


            //Uncomment the below code to Set the message and title from the strings.xml file

            //Setting message manually and performing action on button click
            builder.setMessage("We care about you , please Take an action fast !!")
                    .setCancelable(true)
                    .setPositiveButton("Take an action", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                           // Toast.makeText(getApplicationContext(),"you choose yes action for alertbox",
                                   // Toast.LENGTH_SHORT).show();
                            Intent i =new Intent(add_temp.this, takeActionTemp.class);
                            startActivity(i);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //  Action for 'NO' Button
                            dialog.cancel();
                            Intent i =new Intent(add_temp.this, temp.class);
                            startActivity(i);
                         //   Toast.makeText(getApplicationContext(),"you choose no action for alertbox",
                                  //  Toast.LENGTH_SHORT).show();
                        }
                    });
            //Creating dialog box
            AlertDialog alert = builder.create();
            //Setting the title manually
            alert.setTitle("Your temperature is very high !!");
            alert.setOnShowListener( new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface arg0) {
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    lp.setMargins(0, 0, 15, 0);

                    alert.getButton(AlertDialog.BUTTON_NEGATIVE).setLayoutParams(lp);
                }
            });

            alert.show();
        }
        else{
            Intent i =new Intent(add_temp.this, temp.class);
            startActivity(i);
        }

        Call<ResponseBody> call = RetrofitClient.getInstance().getApi().addTemperature(
                t
        );

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                if (response.isSuccessful()) {
                    // RouteToHome();
                   // Toast.makeText(add_temp.this, "added with sucess", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
               // Toast.makeText(add_temp.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}

