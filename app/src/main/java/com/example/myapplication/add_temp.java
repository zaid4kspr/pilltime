package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.DatePickerDialog;

import androidx.fragment.app.DialogFragment;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.data.model.TemperatureModel;
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

    @BindView(R.id.notesTemp)
    TextInputLayout notesTemp;
    @BindView(R.id.duree_med_form) TextInputLayout temperature;
    String date;
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
//                Intent i =new Intent(add_temp.this, temp.class);
//                startActivity(i);
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

        Call<ResponseBody> call = RetrofitClient.getInstance().getApi().addTemperature(
                t
        );

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                if (response.isSuccessful()) {
                    // RouteToHome();
                    Toast.makeText(add_temp.this, "added with sucess", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(add_temp.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}

