package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.listOfProgramms.listProgAdapter;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.data.model.ProgrammeModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class add_program extends AppCompatActivity {
    RecyclerView recyclerView;
    String prog[];
    int daysLeft[];
    ArrayList<ProgrammeModel> pro ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_program);
        recyclerView = findViewById(R.id.recycleviewProg);
        getAllProgramme();
       // Log.d("testtest",pro.get(2).getMaladie());
        listProgAdapter myAdapter = new listProgAdapter(this, pro);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));






    }
    public void onClickAddProg(View v){

        Intent i =new Intent(getBaseContext(), prog_form.class);
        startActivity(i);
    }
    public void onClickVisitProg(View v){
        Intent i =new Intent(getBaseContext(), edit_instructions_program.class);
        startActivity(i);
    }

    public void getAllProgramme () {
        Call<ArrayList<ProgrammeModel>> call = RetrofitClient.getInstance().getApi().getAllProgramme();
        call.enqueue(new Callback<ArrayList<ProgrammeModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProgrammeModel>> call, Response<ArrayList<ProgrammeModel>> response) {
                if (response.isSuccessful()) {
                    System.out.println(response.code());


                }
                pro = response.body();

            }
            @Override
            public void onFailure(Call<ArrayList<ProgrammeModel>> call, Throwable t) {
                System.out.println("Something went wrong!");

            }
        });
        // Log.d("testtest",p.get(0).getMaladie());

    }
}
