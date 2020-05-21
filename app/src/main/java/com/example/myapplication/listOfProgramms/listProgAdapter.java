package com.example.myapplication.listOfProgramms;
import com.example.myapplication.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.data.model.ProgrammeModel;

import java.util.ArrayList;

public class listProgAdapter extends RecyclerView.Adapter<listProgAdapter.MyViewHolder> {
    private static String TAG = "ADAPTER";
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
        holder.maladie.setText("Program : "+programmeList.get(position).getMaladie());
        holder.desc.setText("Duration of the program : "+programmeList.get(position).getDuree()+ " jour(s)");
       // Log.d("maladiet", programmeList.get(position).getMaladie());
      //  Log.d("maladietd", String.valueOf(programmeList.get(position).getDuree()));
    }
    public void deleteItem(int position) {
        ProgrammeModel mRecentlyDeletedItem = programmeList.get(position);
        int mRecentlyDeletedItemPosition = position;
        programmeList.remove(position);
        notifyItemRemoved(position);
      // showUndoSnackbar();
    }
 /*  private void showUndoSnackbar() {

        LayoutInflater inflater = LayoutInflater.from(context);
        Snackbar snackbar = Snackbar.make(view, "undo",
                Snackbar.LENGTH_LONG);
        snackbar.setAction("undo", v -> undoDelete());
        snackbar.show();
    }*/

  /* private void undoDelete() {
        programmeList.add(mRecentlyDeletedItemPosition,
                mRecentlyDeletedItem);
        notifyItemInserted(mRecentlyDeletedItemPosition);
    }*/

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
    public void addItem(ProgrammeModel item, int position) {
        try {
            programmeList.add(position, item);
            notifyItemInserted(position);
        } catch(Exception e) {
            Log.e("MainActivity", e.getMessage());
        }
    }
    public ProgrammeModel removeItem(int position) {
        ProgrammeModel item = null;
        try {
            item = programmeList.get(position);
            programmeList.remove(position);
            notifyItemRemoved(position);
        } catch(Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return item;
    }

}
