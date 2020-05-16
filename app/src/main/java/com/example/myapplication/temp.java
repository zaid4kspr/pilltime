package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.data.model.TemperatureModel;

import java.util.ArrayList;
import java.util.List;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.view.LineChartView;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.Callback;
import retrofit2.Response;
import com.google.gson.Gson;
import retrofit2.converter.gson.GsonConverterFactory;
import com.google.gson.GsonBuilder;
public class temp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        LineChartView lineChartView = findViewById(R.id.chart);
        List yAxisValues = new ArrayList();
        List axisValues = new ArrayList();
        getAllTemperature(yAxisValues,axisValues);

        List lines = new ArrayList();
        Line line = new Line(yAxisValues).setColor(Color.parseColor("#003366"));
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);
        lineChartView.setLineChartData(data);
        Axis axis = new Axis();
        axis.setValues(axisValues);
        data.setAxisXBottom(axis);
        Axis yAxis = new Axis();
        data.setAxisYLeft(yAxis);
        axis.setTextSize(11);
        axis.setTextColor(Color.parseColor("#03A9F4"));
        yAxis.setTextColor(Color.parseColor("#03A9F4"));
        yAxis.setTextSize(9);


        //Button BtnAdd = findViewById(R.id.addTemp);
        //BtnAdd.setOnClickListener(new View.OnClickListener() {
        //@Override

        //});

    }


    public void onClickAddTemp(View v){
        Intent i =new Intent(getBaseContext(), add_temp.class);
        startActivity(i);
    }
    public void getAllTemperature(List X,List Y) {
        Call<ArrayList<TemperatureModel>> call = RetrofitClient.getInstance().getApi().getAllTemperature();
        call.enqueue(new Callback<ArrayList<TemperatureModel>>() {
            @Override
            public void onResponse(Call<ArrayList<TemperatureModel>> call, Response<ArrayList<TemperatureModel>> response) {
                if (response.isSuccessful()) {

                    System.out.println(response.code());
                    List<TemperatureModel> t = response.body();
                    // do something with books here

                    for(int i = 0; i < t.size(); i++){
                      X.add(t.get(i).getDegres());
                    }
                    Log.d("Degres", String.valueOf(X));
                    for(int i = 0; i < t.size(); i++){
                      Y.add(t.get(i).getDate());
                    }
                    Log.d("Dates", String.valueOf(Y));
                    // Log.d("Temp",String.valueOf(t.get(0).getDegres()));
                    //Toast.makeText(temp.this, "GET SUUUUUucess ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(temp.this, "NOOOOO  TEMP", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<TemperatureModel>> call, Throwable t) {
                System.out.println("Something went wrong!");

            }
        });

    }




}


