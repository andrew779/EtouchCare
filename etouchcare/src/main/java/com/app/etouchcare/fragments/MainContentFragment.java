package com.app.etouchcare.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.etouchcare.R;
import com.app.etouchcare.activity.PatientDetailActivity;
import com.app.etouchcare.adapters.PatientListAdapter;
import com.app.etouchcare.callbacks.PatientListLoadedListener;
import com.app.etouchcare.datamodel.Patients;
import com.app.etouchcare.extra.RecyclerTouchListener;
import com.app.etouchcare.extra.SimpleDividerItemDecoration;
import com.app.etouchcare.tasks.TaskLoadPatientList;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;




/**
 * A simple {@link Fragment} subclass.
 */
public class MainContentFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, PatientListLoadedListener, View.OnClickListener {

    private PatientListAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionMenu menuRed;
    private FloatingActionButton fab1,fab2;
    private OnFetchIDListener mListener;
    private ArrayList<Patients> patientList = new ArrayList<>();


//    public static final String URL_LIST_ALL_PATIENTS = "http://mapd2016.herokuapp.com/";
    public static final String STATE_PATIENTS = "state_patients";
    public static final String PATIENT_LIST = "patient_list";

    public MainContentFragment() {
        // Required empty public constructor
    }

    public interface OnFetchIDListener{
         void onFetchID(String id);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_content, container, false);

        //refresh layout object
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.patientlist_refreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);

        //floating button
        menuRed = (FloatingActionMenu) view.findViewById(R.id.menu_red);
        fab1 = (FloatingActionButton) view.findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) view.findViewById(R.id.fab2);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);

        //recyclerview object
        recyclerView = (RecyclerView) view.findViewById(R.id.patientlist_recyclerview);
        adapter = new PatientListAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                TextView tvID = (TextView) view.findViewById(R.id.patientlist_row_id);
                String str = tvID.getText().toString();
                str = str.substring(str.indexOf(" "));
                if (mListener != null) mListener.onFetchID(str);
                Intent intent = new Intent(getActivity(), PatientDetailActivity.class);
                intent.putExtra("id",str);
                intent.putParcelableArrayListExtra(PATIENT_LIST,patientList);
                startActivity(intent);

            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(),"Long Press on "+position, Toast.LENGTH_SHORT).show();
            }
        }));


        new TaskLoadPatientList(this).execute();


        return view;
    }



    @Override
    public void onRefresh() {
        new TaskLoadPatientList(this).execute();
    }

    @Override
    public void onPatientListLoaded(ArrayList<Patients> patientList) {
        if(swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
        this.patientList = patientList;
        adapter.setPatientList(patientList);
    }

    //floating button click listener
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.fab1:

                Snackbar.make(v,"FAB1 clicked",Snackbar.LENGTH_SHORT).show();
                menuRed.close(true);
                break;
            case R.id.fab2:
                new TaskLoadPatientList(this).execute();
                menuRed.close(true);
                break;
        }
    }

    public void onButtonPressed(String id) {
        if (mListener != null) {
            mListener.onFetchID(id);
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFetchIDListener) {
            mListener = (OnFetchIDListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

}
