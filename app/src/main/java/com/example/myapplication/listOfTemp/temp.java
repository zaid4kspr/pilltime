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
import com.example.myapplication.R;
import com.example.myapplication.RetrofitClient;
import com.example.myapplication.add_temp;
import com.example.myapplication.data.model.TemperatureModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class temp extends AppCompatActivity  {
    @BindView(R.id.listMeds)
    RecyclerView listMedsRview;
    @BindView(R.id.chartTemp)
    LineChart  mChart ;
    ArrayList<Entry> yValues = new ArrayList<>();
    String[] values ;
    ArrayList<Entry> newy = new ArrayList<>();
    String[] newx ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        ButterKnife.bind(this);
        SharedPreferences preferences = getSharedPreferences("myprefs", MODE_PRIVATE);
        String userId = preferences.getString("id", "");
       String userQueryparam = "{\"user\":\"" + userId + "\"}";

        Log.d("userid",userId);
        getListTemperature(userQueryparam);

    }

    public  void setLineGraph(ArrayList<Entry> yValues,  String[] values) {
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(false);
        // Create Limit line
        LimitLine upper_limit = new LimitLine(40f,"");
        upper_limit.setLineWidth(2f);
        upper_limit.enableDashedLine(8f,5f,0f);
        upper_limit.setLabelPosition(LimitLine.LimitLabelPosition.LEFT_TOP);
        upper_limit.setTextSize(15f);
        upper_limit.setTextColor(Color.parseColor("#ff6633"));


        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.addLimitLine(upper_limit);
        leftAxis.setAxisMaximum(45f);
        leftAxis.setAxisMinimum(25f);
        leftAxis.enableGridDashedLine(10f,10f,0);
        leftAxis.setDrawLimitLinesBehindData(true);
        mChart.getAxisRight().setEnabled(false);

        LineDataSet set1 = new LineDataSet(yValues,"Your Temperature");
        set1.setFillAlpha(500);

        set1.setColor(Color.parseColor("#009999"));
        set1.setLineWidth(2.5f);
        set1.setValueTextSize(12f);
        set1.setValueTextColor(Color.parseColor("#008ae6"));
        set1.setCircleRadius(4f);
        set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set1.setFillColor(Color.rgb(177, 210, 175));
        set1.setDrawFilled(true);


        set1.setCircleColor(Color.parseColor("#008ae6"));

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        //Line data
        LineData data = new LineData(dataSets);
        mChart.setData(data);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setValueFormatter(new MyXAxisValueFormatter(values));
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        mChart.notifyDataSetChanged();
        mChart.invalidate();
    }
    public void setData(ArrayList<TemperatureModel> tempList) {

            for(int i = 0; i < tempList.size(); i++){
          yValues.add(new Entry(i,tempList.get(i).getDegres()));
          values[i]=tempList.get(i).getDate().substring(0,10);
        }
        setLineGraph(yValues,values);
        mChart.notifyDataSetChanged();






       // Log.d("valuessyTEST", String.valueOf(yValues));

    }

    public class MyXAxisValueFormatter extends ValueFormatter {
        private String[]  mValues ;
        public  MyXAxisValueFormatter(String[]  values){
            this.mValues=values;
        }
        @Override
        public String getAxisLabel(float value, AxisBase axis) {
            try {
                return  mValues[(int)value];            }
            catch(ArrayIndexOutOfBoundsException e) {
            return  "Error";
            }

        }


    }

    public void onClickAddTemp(View v){
        Intent i =new Intent(getBaseContext(), add_temp.class);
        startActivity(i);
    }

    public void getListTemperature(String userQueryparam) {


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
                    values=new String[tempList.size()];
                    newx=new String[tempList.size()];
                    // add data
                    if (tempList.size()>0) {
                        setData(tempList);

                    }else{

                        setLineGraph(newy,  newx);
                        mChart.notifyDataSetChanged();

                    }
                   // Log.d("valuess", String.valueOf(values));
                   // Log.d("valuessy", String.valueOf(yValues));


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


