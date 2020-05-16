package com.example.myapplication.listOfTemp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import  android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.data.model.TemperatureModel;

import java.util.ArrayList;

public class listTempAdapter extends RecyclerView.Adapter<listTempAdapter.MyViewHolder> {

    ArrayList<TemperatureModel> temperatureList = new ArrayList<TemperatureModel>();
    Context context;
    public listTempAdapter(Context ct, ArrayList<TemperatureModel> t) {

        context = ct;
        temperatureList = t;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.temp_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.maladie.setText(Float.toString(temperatureList.get(position).getDegres()));
        if (temperatureList.get(position).getDegres()>40) {
            holder.maladie.setTextColor(Color.rgb(255, 71, 26));
        }
        holder.desc.setText(temperatureList.get(position).getDate());




    }

    @Override
    public int getItemCount() {
        return temperatureList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView maladie, desc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            maladie = itemView.findViewById(R.id.tempDEG);
            desc = itemView.findViewById(R.id.tempDATE);


        }
    }

}
