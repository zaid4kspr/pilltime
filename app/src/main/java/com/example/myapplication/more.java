package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class more extends Fragment {
    @BindView(R.id.Measurements)
    LinearLayout goToMeas;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        ButterKnife.bind(this, view);
        // TODO Use fields...
        return view;

    }
    @OnClick(R.id.Measurements)
    public void submit() {
        Intent i =new Intent(getActivity(), measurements.class);
        startActivity(i);

    }
    @OnClick(R.id.Report)
    public void submitReport() {
        Intent i =new Intent(getActivity(), SMS.class);
        startActivity(i);

    }


}
