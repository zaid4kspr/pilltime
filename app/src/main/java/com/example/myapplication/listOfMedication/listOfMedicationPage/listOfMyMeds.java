package com.example.myapplication.listOfMedication.listOfMedicationPage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.RetrofitClient;
import com.example.myapplication.data.model.MedicamentModel;
import com.example.myapplication.data.model.PriseModel;
import com.example.myapplication.measurements;
import com.example.myapplication.med_form;
import com.google.android.material.button.MaterialButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class listOfMyMeds extends Fragment {

    @BindView(R.id.listMeds) RecyclerView listMedsRview;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_of_meds, container, false);
        ButterKnife.bind(this, view);

        SharedPreferences preferences = this.getActivity().getSharedPreferences("myprefs", getContext().MODE_PRIVATE);
        String userId = preferences.getString("id", "");


        String userQueryparam = "{\"user\":\"" + userId + "\"}";

        getListOfMeds(userQueryparam);



        return view;
    }


    public void getListOfMeds(String userQueryparam) {


        Call<ArrayList<MedicamentModel>> call = RetrofitClient.getInstance().getApi().getMyMedsLotf3leya(

                userQueryparam

        );
        call.enqueue(new Callback<ArrayList<MedicamentModel>>() {
            @Override
            public void onResponse(Call<ArrayList<MedicamentModel>> call, Response<ArrayList<MedicamentModel>> response) {
                if (response.isSuccessful()) {

                    System.out.println(response.code());
                    ArrayList<MedicamentModel> medsList = response.body();
                    // do something with books here

                    listOfMyMedsAdapter myAdapter = new listOfMyMedsAdapter(getContext(), medsList);
                    listMedsRview.setAdapter(myAdapter);
                    listMedsRview.setLayoutManager(new LinearLayoutManager(getContext()));

                } else {
                }
            }

            @Override
            public void onFailure(Call<ArrayList<MedicamentModel>> call, Throwable t) {
                System.out.println("Something went wrong!");

            }
        });


    }


    @OnClick(R.id.add)
    public void addMeds(){
        Intent i =new Intent(getActivity(), med_form.class);
        startActivity(i);
    }

}
