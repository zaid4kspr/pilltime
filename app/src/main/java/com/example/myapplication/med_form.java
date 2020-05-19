package com.example.myapplication;

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

public class med_form extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {

    @BindView(R.id.dateDebut)
    TextInputLayout dateDebut;
    @BindView(R.id.name)
    TextInputLayout medName;
    @BindView(R.id.duree_med_form)
    TextInputLayout duree_med_form;
    Date dateDebutJava;
    String programme;
    Spinner spinner;
    String userId;
    ArrayAdapter<String> adapter;
    ArrayList<String> maladie = new ArrayList<String>();
    ArrayList<ProgrammeModel> programmeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_form);

        SharedPreferences preferences = getSharedPreferences("myprefs", MODE_PRIVATE);
        userId = preferences.getString("id", "");

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.dialTake);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(med_form.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_take, null);
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });

        ButterKnife.bind(this);
        getListOfProgrammes();


// Create an ArrayAdapter using the string array and a default spinner layout
//
//        Button BtnAdd = findViewById(R.id.addBtn);
//        BtnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(med_form.this, activity_list_med.class);
//                startActivity(i);
//            }
//        });


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

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)

        programme= programmeList.get(pos).getId();




    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
                    if(programmeList.size()==0){
                        routeToCreateProgram();
                    }

                    // do something with books here
                    maladie.clear();
                    for (Integer i = 0; i < programmeList.size(); i++) {
                        maladie.add(programmeList.get(i).getMaladie());
                    }


                } else {
                    System.out.println("Something went wrong!");
                }

                fillSpinnerItems();

            }

            @Override
            public void onFailure(Call<ArrayList<ProgrammeModel>> call, Throwable t) {
                System.out.println("Something went wrong!");

            }
        });


    }

    public void fillSpinnerItems(){
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        // Create an ArrayAdapter using the string array and a default spinner layout
        adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, maladie);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }


    @OnClick(R.id.addBtn)
    public void addProgram() {
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

        MedicamentModel t = new MedicamentModel(datedebutLocal,dateFin,name,duree,userId,programme);

        Call<ResponseBody> call = RetrofitClient.getInstance().getApi().addMeds(
                t
        );

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                if (response.isSuccessful()) {
                    // RouteToHome();
                    Toast.makeText(med_form.this, "added with sucess", Toast.LENGTH_SHORT).show();
                    routeToMain();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(med_form.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }



    void routeToCreateProgram(){
        Toast.makeText(this, "You have to create a programm first", Toast.LENGTH_SHORT).show();
        routeToMain();


    }


    void routeToMain(){
        Intent i =new Intent(getBaseContext(), MainActivity.class);
        startActivity(i);
    }

}

