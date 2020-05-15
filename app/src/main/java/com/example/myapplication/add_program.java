package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.data.model.ProgrammeModel;
import com.example.myapplication.data.model.TemperatureModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class add_program extends AppCompatActivity {
    ArrayList<String> X ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_program);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.listProg);
        ArrayList<String> test = new ArrayList<String>();
       X = new ArrayList<String>();
     getAllProgramme();

        for (int i=0 ; i<5 ; i++) {
            TextView textView1 = new TextView(this);
            textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textView1.setText(X.get(0).toString());
            textView1.setBackgroundColor(0xff66ff66); // hex color 0xAARRGGBB
            textView1.setPadding(20, 20, 20, 20);// in pixels (left, top, right, bottom)
            linearLayout.addView(textView1);
        }



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
                    // do something with books here
                   // List Y = new ArrayList();

                   // for(int i = 0; i < p.size(); i++){
                    //    Y.add(p.get(i).getDuree());
                   // }
                  //  Log.d("DUREEE", String.valueOf(Y));
                    // Log.d("Temp",String.valueOf(t.get(0).getDegres()));
                    //Toast.makeText(temp.this, "GET SUUUUUucess ", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(add_program.this, "NOOOOO  Progggg", Toast.LENGTH_SHORT).show();
                }
                List<ProgrammeModel> p = response.body();
                for(int i = 0; i < p.size(); i++){
                    X.add(p.get(i).getMaladie());
                    Log.d("MALADIE", X.get(i));

                }
            }
            @Override
            public void onFailure(Call<ArrayList<ProgrammeModel>> call, Throwable t) {
                System.out.println("Something went wrong!");

            }
        });




    }
}
