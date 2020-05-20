package com.example.myapplication.listOfMedication.listOfMedicationPage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.R;
import com.example.myapplication.RetrofitClient;
import com.example.myapplication.data.model.MedicamentModel;

import com.example.myapplication.med_form;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.button.MaterialButton;


import com.example.myapplication.dialog_take;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class listOfMyMeds extends Fragment implements listOfMyMedsAdapter.onMedListener {

    @BindView(R.id.listMeds) RecyclerView listMedsRview;
    ArrayList<MedicamentModel> medsList;
    private ShimmerFrameLayout mShimmerViewContainer;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_of_meds, container, false);
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);

        mShimmerViewContainer.startShimmer();
        ButterKnife.bind(this, view);

        SharedPreferences preferences = this.getActivity().getSharedPreferences("myprefs", getContext().MODE_PRIVATE);
        String userId = preferences.getString("id", "");


        String userQueryparam = "{\"user\":\"" + userId + "\"}";

        getListOfMeds(userQueryparam);



        return view;
    }


    public void getListOfMeds(String userQueryparam) {


        Call<ArrayList<MedicamentModel>> call = RetrofitClient.getInstance().getApi().getMyMedsLotf3leya(

                userQueryparam

        );
        call.enqueue(new Callback<ArrayList<MedicamentModel>>() {
            @Override
            public void onResponse(Call<ArrayList<MedicamentModel>> call, Response<ArrayList<MedicamentModel>> response) {
                if (response.isSuccessful()) {
                    mShimmerViewContainer.stopShimmer();
                    mShimmerViewContainer.setVisibility(View.GONE);
                    System.out.println(response.code());
                    medsList = response.body();
                    // do something with books here

                    setTheAdapter();


                } else {
                }
            }

            @Override
            public void onFailure(Call<ArrayList<MedicamentModel>> call, Throwable t) {
                System.out.println("Something went wrong!");

            }
        });


    }


    @OnClick(R.id.add)
    public void addMeds(){
        Intent i =new Intent(getActivity(), med_form.class);
        startActivity(i);
    }

    @Override
    public void onMedicationClick(int position) {

       // Toast.makeText(getContext(), medsList.get(position).getName(), Toast.LENGTH_SHORT).show();

        Intent i = new Intent(getContext(),dialog_take.class);
        i.putExtra("name", medsList.get(position).getName());
        i.putExtra("id", medsList.get(position).getId());
        i.putExtra("dateDebut", medsList.get(position).getDateDebut());
        i.putExtra("Duree", medsList.get(position).getDuree().toString());
        i.putExtra("program", medsList.get(position).getProgramme());
        i.putExtra("user", medsList.get(position).getUser());
        startActivity(i);


    }


    public void setTheAdapter( ){
        listOfMyMedsAdapter myAdapter = new listOfMyMedsAdapter(getContext(), medsList,this);
        listMedsRview.setAdapter(myAdapter);
        listMedsRview.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
