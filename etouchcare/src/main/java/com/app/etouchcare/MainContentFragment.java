package com.app.etouchcare;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainContentFragment extends Fragment {

    PatientListAdapter adapter;
    private RecyclerView recyclerView;


    public MainContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_content, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.patientlist_recyclerview);

        adapter = new PatientListAdapter(getActivity(),getData());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity() ));
        recyclerView.setAdapter(adapter);

        return view;
    }

    public static List<Data> getData(){
        List<Data> data = new ArrayList<>();
        int[] icon = {R.drawable.profile,R.drawable.logo,R.drawable.profile,R.drawable.logo};
        String[] name = {"abc","bcd","Mark","Micheal"};
        String[] des = {"This is a long description","This is a short description",
                "This is a long description","This is a short description"};
        for(int i = 0; i < 100; i++){
            Data current = new Data();
            current.setIconID(icon[i%name.length]);
            current.setpName(name[i%name.length]);
            current.setDescription(des[i%name.length]);
            data.add(current);


        }

        return data;
    }

}
