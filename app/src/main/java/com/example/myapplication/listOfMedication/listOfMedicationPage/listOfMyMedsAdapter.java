package com.example.myapplication.listOfMedication.listOfMedicationPage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.data.model.MedicamentModel;
import com.example.myapplication.data.model.PriseModel;

import java.util.ArrayList;

public class listOfMyMedsAdapter extends RecyclerView.Adapter<listOfMyMedsAdapter.MyViewHolder> {


    ArrayList<MedicamentModel> medsList = new ArrayList<MedicamentModel>();
    Context context;

    public listOfMyMedsAdapter(Context ct, ArrayList<MedicamentModel> p) {

        context = ct;
        medsList = p;

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
        holder.name.setText(medsList.get(position).getName());
        holder.desc.setText("Date de début : "+medsList.get(position).getDateDebut().substring(0,10));
    }

    @Override
    public int getItemCount() {
        return medsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, desc,heure;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.medicationName);
            desc = itemView.findViewById(R.id.medicationDesc);



        }
    }

}
