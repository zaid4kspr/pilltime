package com.example.myapplication.listOfMedication.listOfMedicationPage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.data.model.MedicamentModel;
import com.example.myapplication.data.model.PriseModel;
import com.example.myapplication.med_form;

import java.util.ArrayList;

import butterknife.OnClick;

public class listOfMyMedsAdapter extends RecyclerView.Adapter<listOfMyMedsAdapter.MyViewHolder>  {


    ArrayList<MedicamentModel> medsList = new ArrayList<MedicamentModel>();
    onMedListener myOnMedListener ;
    Context context;

    public listOfMyMedsAdapter(Context ct, ArrayList<MedicamentModel> p,onMedListener m) {

        context = ct;
        medsList = p;
       this. myOnMedListener=m;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.med_item, parent, false);
        return new MyViewHolder(view,myOnMedListener);
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

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name, desc,heure;
        onMedListener onMedListener ;

        public MyViewHolder(@NonNull View itemView,onMedListener onMedListener) {
            super(itemView);
            name = itemView.findViewById(R.id.medicationName);
            desc = itemView.findViewById(R.id.medicationDesc);
           this. onMedListener=onMedListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onMedListener.onMedicationClick(getAdapterPosition());
        }
    }


    public interface onMedListener {
        void onMedicationClick(int position);
    }

}
