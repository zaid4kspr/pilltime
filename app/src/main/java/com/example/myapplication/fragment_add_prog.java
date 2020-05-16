package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.widget.TextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Gravity;

import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import android.util.TypedValue;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.ui.login.LoginActivity;
import com.google.android.material.button.MaterialButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class fragment_add_prog extends Fragment {
    @BindView(R.id.AddProgram) MaterialButton button1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_add_prog, container, false);

        ButterKnife.bind(this, view);
        // TODO Use fields...
        return view;


    }



    @OnClick(R.id.AddProgram)
    public void submit() {
        Intent i =new Intent(getActivity(), add_program.class);
        startActivity(i);

    }}
