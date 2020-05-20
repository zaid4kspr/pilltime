package com.example.myapplication.listOfProgramms;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;


import com.example.myapplication.R;
import com.example.myapplication.RetrofitClient;
import com.example.myapplication.data.model.ProgrammeModel;
import com.example.myapplication.prog_form;

import androidx.recyclerview.widget.DividerItemDecoration;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.snackbar.Snackbar
        ;
import java.util.ArrayList;


import butterknife.BindView;
import butterknife.ButterKnife;

import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class listOfPrograms  extends Fragment {

    private ShimmerFrameLayout mShimmerViewContainer;



    @BindView(R.id.listMeds)
    RecyclerView listMedsRview;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_prog_list, container, false);

        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);


        mShimmerViewContainer.startShimmer();


        ButterKnife.bind(this, view);



        SharedPreferences preferences = this.getActivity().getSharedPreferences("myprefs", getContext().MODE_PRIVATE);
        String userId = preferences.getString("id", "");
        String userQueryparam = "{\"user\":\"" + userId + "\"}";
        getListOfProgram(userQueryparam);
        return view;
    }
    @OnClick(R.id.addProg)
    public void onClickAddProgramme(View v){

        Intent i =new Intent(getActivity(), prog_form.class);
        startActivity(i);
    }



    public void getListOfProgram(String userQueryparam) {


        Call<ArrayList<ProgrammeModel>> call = RetrofitClient.getInstance().getApi().getProgramme(

                userQueryparam


        );
        call.enqueue(new Callback<ArrayList<ProgrammeModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProgrammeModel>> call, Response<ArrayList<ProgrammeModel>> response) {
                if (response.isSuccessful()) {

                    mShimmerViewContainer.stopShimmer();
                    mShimmerViewContainer.setVisibility(View.GONE);

                    ArrayList<ProgrammeModel> programmList = response.body();
                    // do something with books here
                    listMedsRview.setLayoutManager(new LinearLayoutManager(getContext()));
                    listProgAdapter myAdapter = new listProgAdapter(getContext(), programmList);
                    listMedsRview.setAdapter(myAdapter);
                    ItemTouchHelper itemTouchHelper = new
                            ItemTouchHelper(new SwipeToDeleteCallback(myAdapter));
                    itemTouchHelper.attachToRecyclerView(listMedsRview);

                    // listMedsRview.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));

                }

            }

            @Override
            public void onFailure(Call<ArrayList<ProgrammeModel>> call, Throwable t) {
                System.out.println("Something went wrong!");

            }
        }
        );


    }

}
