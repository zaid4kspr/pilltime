package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.view.LineChartView;

public class temp extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        LineChartView lineChartView = findViewById(R.id.chart);
        String[] axisData = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        int[] yAxisData = {40, 38, 35, 34, 38, 42, 43};
        List yAxisValues = new ArrayList();
        List axisValues = new ArrayList();
        Line line = new Line(yAxisValues).setColor(Color.parseColor("#003366"));
        for(int i = 0; i < axisData.length; i++){
            axisValues.add(i, new AxisValue(i).setLabel(axisData[i]));
        }

        for (int i = 0; i < yAxisData.length; i++){
            yAxisValues.add(new PointValue(i, yAxisData[i]));
        }
        List lines = new ArrayList();
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





}


