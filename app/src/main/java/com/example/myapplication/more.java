package com.example.myapplication;
import com.example.myapplication.listOfTemp.temp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.ui.login.LoginActivity;
import com.google.android.material.button.MaterialButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.MODE_PRIVATE;


public class more extends Fragment {
    @BindView(R.id.Measurements)
    LinearLayout goToMeas;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        //



        ButterKnife.bind(this, view);
        // TODO Use fields...
        return view;

    }

    int countTemp(){

        return 5;

    }
    @OnClick(R.id.Measurements)
    public void submit() {
        if(countTemp()==0){
            Intent i =new Intent(getActivity(), measurements.class);
            startActivity(i);
        }else{
            Intent i =new Intent(getActivity(), temp.class);
            startActivity(i);
        }



    }
    @OnClick(R.id.Report)
    public void submitReport() {
        Intent i =new Intent(getActivity(), SMS.class);
        startActivity(i);

    }
    @OnClick(R.id.Doctors)
    public void submitMap() {
        Intent i =new Intent(getActivity(), myposition.class);
        startActivity(i);

    }
    @OnClick(R.id.Deco)
    public void deco() {
        SharedPreferences pref = this.getActivity().getSharedPreferences("BuyyaPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit(); // commit changes


         Intent i =new Intent(getActivity(), LoginActivity.class);
        startActivity(i);

    }




}
