package com.example.myapplication.listOfTemp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.RetrofitClient;
import com.example.myapplication.add_temp;
import com.example.myapplication.data.model.TemperatureModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.view.LineChartView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class temp extends AppCompatActivity {
    @BindView(R.id.listMeds)
    RecyclerView listMedsRview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        ButterKnife.bind(this);


        SharedPreferences preferences =getSharedPreferences("myprefs",MODE_PRIVATE);
        String userId = preferences.getString("id", "");
        String userQueryparam = "{\"user\":\"" + userId + "\"}";


        getTemperature(userQueryparam);



        //Button BtnAdd = findViewById(R.id.addTemp);
        //BtnAdd.setOnClickListener(new View.OnClickListener() {
        //@Override

        //});

    }


    public void onClickAddTemp(View v){
        Intent i =new Intent(getBaseContext(), add_temp.class);
        startActivity(i);
    }

    public void getTemperature(String userQueryparam) {


        Call<ArrayList<TemperatureModel>> call = RetrofitClient.getInstance().getApi().getTemperature(

                userQueryparam


        );
        call.enqueue(new Callback<ArrayList<TemperatureModel>>() {
            @Override
            public void onResponse(Call<ArrayList<TemperatureModel>> call, Response<ArrayList<TemperatureModel>> response) {
                if (response.isSuccessful()) {

                    System.out.println(response.code());
                    ArrayList<TemperatureModel> tempList = response.body();

                    listTempAdapter myAdapter = new listTempAdapter(getBaseContext(), tempList);
                    listMedsRview.setAdapter(myAdapter);
                    listMedsRview.setLayoutManager(new LinearLayoutManager(getBaseContext()));

                } else {
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TemperatureModel>> call, Throwable t) {
                System.out.println("Something went wrong!");

            }
        });


    }




}


