package com.example.myapplication.listMeds;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.RetrofitClient;
import com.example.myapplication.data.model.MedicamentModel;
import com.example.myapplication.data.model.PriseModel;
import com.example.myapplication.data.model.TemperatureModel;
import com.example.myapplication.temp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class listOfMeds extends Fragment {

    @BindView(R.id.listMeds)
    RecyclerView listMedsRview;

    ArrayList<PriseModel> priseList = new ArrayList<PriseModel>();




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_calendar_page, container, false);
        ButterKnife.bind(this, view);

        SharedPreferences preferences = this.getActivity().getSharedPreferences("myprefs", getContext().MODE_PRIVATE);
        String userId = preferences.getString("id", "");

        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 50);


        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(view, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();


        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                Calendar cal = horizontalCalendar.getDateAt(position);
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                String now = format1.format(cal.getTime());
                cal.add(Calendar.DATE,1);
                String tomorrow = format1.format(cal.getTime());

                Toast.makeText(getContext(), ""+now+"---"+tomorrow, Toast.LENGTH_SHORT).show();
            }


            @Override
            public boolean onDateLongClicked(Calendar date, int position) {
                return true;
            }
            
        });

//        MedicamentModel m = new MedicamentModel("aa",  "54",  "aaa",  "aa", 55, "a", "a");
//
//        PriseModel p1 = new PriseModel("zaaz", "2020-05-18T08:00:00", "08:00 AM", 2, m, "");
//        PriseModel p2 = new PriseModel("zaaz", "2020-05-18T08:00:00", "12:00 AM", 1, m, "");
//        PriseModel p3 = new PriseModel("zaaz", "2020-05-18T08:00:00", "21:00 AM", 1, m, "");
//
//        priseList.add(p1);
//        priseList.add(p2);
//        priseList.add(p3);
// https://pill-time.herokuapp.com/api/prise?query={ "date" : {"$gt": "2020-05-14" ,"$lt": "2020-05-15"}}
      // String userQueryparam="{\"user\":\""+userId+"\",\"date\":{\"2020-05-18T08:00\"}}";
       String userQueryparam="{\"user\":\"\"+userId+\"\", " +
               "\"date\" : {\"$gt\": \"2020-05-14\" ,\"$lt\": \"2020-05-15\"}}";


       String DateQuery="{\"date\":{\"2020-05-18T08:00\"}}";
        Call<ArrayList<PriseModel>> call = RetrofitClient.getInstance().getApi().getPrise(

                userQueryparam,
                "ref_med"

        );
        call.enqueue(new Callback<ArrayList<PriseModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PriseModel>> call, Response<ArrayList<PriseModel>> response) {
                if (response.isSuccessful()) {

                    System.out.println(response.code());
                    ArrayList<PriseModel> priseList = response.body();
                    // do something with books here

                    listMedsAdapter myAdapter = new listMedsAdapter(getContext(), priseList);
                    listMedsRview.setAdapter(myAdapter);
                    listMedsRview.setLayoutManager(new LinearLayoutManager(getContext()));
                    Toast.makeText(getContext(), "SUCCESS", Toast.LENGTH_SHORT).show();

                } else {
                }
            }
            @Override
            public void onFailure(Call<ArrayList<PriseModel>> call, Throwable t) {
                System.out.println("Something went wrong!");

            }
        });









        return view;
    }


    public void getListOfMeds() {

    }


}
