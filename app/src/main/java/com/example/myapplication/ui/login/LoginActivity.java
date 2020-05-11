package com.example.myapplication.ui.login;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.RetrofitClient;
import com.example.myapplication.data.model.ResponseObject;
import com.example.myapplication.register;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    @BindView(R.id.email) TextInputLayout email ;
    @BindView(R.id.password) TextInputLayout  password ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        ButterKnife.bind(this);

        final Button register = findViewById(R.id.registerNowBtn);
        final Button loginBtn = findViewById(R.id.loginBtn);



        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                routeToRegister();
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here

                login();
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


    public void login(){
        String email = this.email.getEditText().getText().toString();
        String password = this.password.getEditText().getText().toString();


        Call<ResponseObject> call = RetrofitClient.getInstance().getApi().login(
                email,
                password

        );
        call.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                if(response.isSuccessful()){
                    response.body(); // have your all data
                    String name =response.body().getUser().getName();
                    Toast.makeText(LoginActivity.this, "Hello  " + name, Toast.LENGTH_SHORT).show();


                }else{

                    Toast.makeText(LoginActivity.this,   "Email / Password incorrect", Toast.LENGTH_SHORT).show();

                }

            }



            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {

            }
        });



    }




}
