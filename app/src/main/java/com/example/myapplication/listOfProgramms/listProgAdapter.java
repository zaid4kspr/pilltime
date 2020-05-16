package com.example.myapplication.listOfProgramms;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.data.model.ProgrammeModel;

import java.util.ArrayList;

public class listProgAdapter extends RecyclerView.Adapter<listProgAdapter.MyViewHolder> {

    ArrayList<ProgrammeModel> programmeList = new ArrayList<ProgrammeModel>();
    Context context;
    public listProgAdapter(Context ct, ArrayList<ProgrammeModel> p) {

        context = ct;
        programmeList = p;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.prog_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.maladie.setText(programmeList.get(position).getMaladie());
        holder.desc.setText(programmeList.get(position).getDuree()+" jours restants");
        Log.d("maladiet", programmeList.get(position).getMaladie());
        Log.d("maladietd", String.valueOf(programmeList.get(position).getDuree()));



    }

    @Override
    public int getItemCount() {
        return programmeList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView maladie, desc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            maladie = itemView.findViewById(R.id.maladieName);
            desc = itemView.findViewById(R.id.progRest);


        }
    }

}
