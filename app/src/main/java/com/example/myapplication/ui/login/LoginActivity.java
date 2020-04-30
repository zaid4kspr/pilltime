package com.example.myapplication.ui.login;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.register;

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
        final Button loginBtn = findViewById(R.id.loginBtn);
        // final ProgressBar loadingProgressBar = findViewById(R.id.loading);



        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                routeToRegister();
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here

                routeAfterLogin();
            }
        });

    }


    public void routeToRegister(){

        Intent i = new Intent(this, register.class);
        startActivity(i);

    }
    public void routeAfterLogin(){

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }

}
