package com.example.myapplication.listMeds;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;


public class listOfMeds extends Fragment {

    @BindView(R.id.listMeds) RecyclerView listMedsRview;
    ArrayList <String> s1=new ArrayList<String>(   Arrays.asList
            ("Vicodin","Neurontin ","Glucophage ",
"Zofran ", "maxilaze",
                    "Vicodin","Neurontin ","Glucophage ",
                    "Zofran ", "maxilaze"
            ) ) ;
    ArrayList <String> s2=new ArrayList<String>(   Arrays.asList(
            "zouz 7rabech ba3ed la3che",
            "7arboucha sbeh",
            "Take 1 application(s)",
            "7ribicha nos nhar",
            "joghma me dabouza direct",

            "zouz 7rabech ba3ed la3che",
            "7arboucha sbeh",
            "Take 1 application(s)",
            "7ribicha nos nhar",
            "joghma me dabouza direct"
    ) ) ;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
 View view= inflater.inflate(R.layout.fragment_list_of_meds, container, false);
        ButterKnife.bind(this, view);

        listMedsAdapter myAdapter=new listMedsAdapter(getContext(),s1,s2);
        listMedsRview.setAdapter(myAdapter);
        listMedsRview.setLayoutManager(new LinearLayoutManager(getContext()));

return view;
    }


}
