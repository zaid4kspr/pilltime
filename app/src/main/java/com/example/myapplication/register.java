package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.ui.login.LoginActivity;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class register extends AppCompatActivity {

    @BindView(R.id.email) TextInputLayout email ;
    @BindView(R.id.password) TextInputLayout  password ;
    @BindView(R.id.birthYear) TextInputLayout  birthYear ;
    @BindView(R.id.name) TextInputLayout  name ;
    @BindView(R.id.phone) TextInputLayout  phone ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);


        final Button button = (Button) findViewById(R.id.register);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                register();
            }
        });



    }

public void register(){

    String email = this.email.getEditText().getText().toString();
    String password = this.password.getEditText().getText().toString();
    String name = this.name.getEditText().getText().toString();
    //String sexe = this.sexe.getEditText().getText().toString();
    String birthYear = this.birthYear.getEditText().getText().toString();

    Call<ResponseBody> call = RetrofitClient.getInstance().getApi().register(
            email,
            password,
            name,
            0,
            birthYear
    );

    call.enqueue(new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            if(response.isSuccessful()){
                RouteToLogin();
                Toast.makeText(register.this, "Resgister with sucess", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            Toast.makeText(register.this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });

}

public void RouteToLogin(){
    Intent i = new Intent(this, LoginActivity.class);
    startActivity(i);
}

    public void routeToMain(){

//    Intent i = new Intent(register.this,addMed.class);
//    startActivity(i);

    }
}
