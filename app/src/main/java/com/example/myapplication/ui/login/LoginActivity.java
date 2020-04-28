package com.example.myapplication.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.addMed;
import com.example.myapplication.register;
import com.example.myapplication.ui.login.LoginViewModel;
import com.example.myapplication.ui.login.LoginViewModelFactory;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

//        final EditText emailEditText = findViewById(R.id.email);
//        final EditText passwordEditText = findViewById(R.id.pw);
        final Button loginButton = findViewById(R.id.loginBtn);

        final Button register = findViewById(R.id.registerNowBtn);
        // final ProgressBar loadingProgressBar = findViewById(R.id.loading);



        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                routeToRegister();
            }
        });

    }


    public void routeToRegister(){

        Intent i = new Intent(this, register.class);
        startActivity(i);

    }

}
