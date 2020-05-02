package com.example.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;

import butterknife.ButterKnife;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;


public class HomeCalendarPage extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 50);


        View viewRoot = inflater.inflate(R.layout.fragment_home_calendar_page, container, false);
        ButterKnife.bind(this, viewRoot);
        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(viewRoot, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
              
                .build();



        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {

            }



            @Override
            public boolean onDateLongClicked(Calendar date, int position) {
                return true;
            }
        });










        // TODO Use fields...
        return viewRoot;


    }






}
