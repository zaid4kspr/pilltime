package com.example.myapplication.listOfPriseHomePage;

import android.app.Notification;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
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
import com.example.myapplication.data.model.PriseModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.alerteReceiverPack.App.CHANNEL_1_ID;
import static com.example.myapplication.alerteReceiverPack.App.CHANNEL_2_ID;


public class listOfPriseHomePage extends Fragment {

    @BindView(R.id.listMeds)
    RecyclerView listMedsRview;
    private ShimmerFrameLayout mShimmerViewContainer;
    private NotificationManagerCompat notificationManager;

    ArrayList<PriseModel> priseList = new ArrayList<PriseModel>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_calendar_page, container, false);
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);

       // notificationManager = NotificationManagerCompat.from(getContext());


        mShimmerViewContainer.startShimmer();

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
                cal.add(Calendar.DATE, 1);
                String tomorrow = format1.format(cal.getTime());


                String userQueryparam = "{\"user\":\"" + userId + "\", " + "\"date\" : {\"$gte\": \"" + now + "\" ,\"$lt\": \"" + tomorrow + "\"}}";

              //sendOnChannel1();
                getListOfMeds(userQueryparam);

            }


            @Override
            public boolean onDateLongClicked(Calendar date, int position) {
                return true;
            }

        });


        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date1);

        String dateDebut = dateFormat.format(date1);
        c.add(Calendar.DATE, 1);


        String dateFin = dateFormat.format(c.getTime());


        String userQueryparam = "{\"user\":\"" + userId + "\", " + "\"date\" : {\"$gte\": \"" + dateDebut + "\" ,\"$lt\": \"" + dateFin + "\"}}";
        Log.d("zzzz", userQueryparam);

          getListOfMeds(userQueryparam);

        return view;
    }


    public void getListOfMeds(String userQueryparam) {


        Call<ArrayList<PriseModel>> call = RetrofitClient.getInstance().getApi().getPrise(

                userQueryparam,
                "ref_med"

        );
        call.enqueue(new Callback<ArrayList<PriseModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PriseModel>> call, Response<ArrayList<PriseModel>> response) {
                if (response.isSuccessful()) {
                    mShimmerViewContainer.stopShimmer();
                    mShimmerViewContainer.setVisibility(View.GONE);
                    System.out.println(response.code());
                    ArrayList<PriseModel> priseList = response.body();
                    // do something with books here
                    listMedsAdapter myAdapter = new listMedsAdapter(getContext(), priseList);
                    listMedsRview.setAdapter(myAdapter);
                    listMedsRview.setLayoutManager(new LinearLayoutManager(getContext()));

                } else {
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PriseModel>> call, Throwable t) {
                System.out.println("Something went wrong!");

            }
        });


    }


//    @Override
//    public void onResume() {
//        super.onResume();
//    }
//
//    @Override
//    public void onPause() {
//        mShimmerViewContainer.stopShimmerAnimation();
//        super.onPause();
//    }



    public void sendOnChannel1() {
        String title = "Pill Time";
        String message = "Ochreb Dwek Ya mridhh";
        Notification notification = new NotificationCompat.Builder(getContext(), CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_pill)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                .build();
        notificationManager.notify(1, notification);
    }

    public void sendOnChannel2() {
        String title = "test";
        String message = "test";
        Notification notification = new NotificationCompat.Builder(getContext(), CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_pill)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();
        notificationManager.notify(2, notification);
    }

}
