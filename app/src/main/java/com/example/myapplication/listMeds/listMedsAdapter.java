package com.example.myapplication.listMeds;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.data.model.PriseModel;

import java.util.ArrayList;

public class listMedsAdapter extends RecyclerView.Adapter<listMedsAdapter.MyViewHolder> {


    ArrayList<PriseModel> priseList = new ArrayList<PriseModel>();
    Context context;

    public listMedsAdapter(Context ct, ArrayList<PriseModel> p) {

        context = ct;
        priseList = p;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.med_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(priseList.get(position).getRefMed().getName());
        holder.desc.setText("Take "+priseList.get(position).getQte()+" application(s)");
        holder.heure.setText(priseList.get(position).getHeure());
    }

    @Override
    public int getItemCount() {
        return priseList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, desc,heure;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.medicationName);
            desc = itemView.findViewById(R.id.medicationDesc);
            heure = itemView.findViewById(R.id.heure);


        }
    }

}
