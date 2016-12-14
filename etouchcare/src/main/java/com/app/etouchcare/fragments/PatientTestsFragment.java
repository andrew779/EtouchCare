/**
 * TeamOne
 */
package com.app.etouchcare.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.etouchcare.R;
import com.app.etouchcare.activity.AddPatient;
import com.app.etouchcare.activity.AddTest;
import com.app.etouchcare.adapters.PatientTestAdapter;
import com.app.etouchcare.callbacks.PatientLoadedListener.PatientTestLoadedListener;
import com.app.etouchcare.datamodel.Patients;
import com.app.etouchcare.datamodel.Test;
import com.app.etouchcare.extra.PatientUtils;
import com.app.etouchcare.extra.SimpleDividerItemDecoration;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PatientTestsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PatientTestsFragment extends Fragment implements PatientTestLoadedListener, View.OnClickListener{
    private RecyclerView recyclerView;
    private PatientTestAdapter patientTestAdapter;
    private PatientUtils patientUtils;
    private FloatingActionMenu menuRed;
    private FloatingActionButton fab_test_add, fab2;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PATIENT_ID = "ARG_PATIENT_ID";
    private static final String ARG_THEONE = "ARG_THEONE";

    private String patientID;
    private Patients theOne;

    public PatientTestsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PatientTestsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PatientTestsFragment newInstance(String id,Patients patients) {
        Bundle args = new Bundle();
        args.putString(ARG_PATIENT_ID, id);
        args.putParcelable(ARG_THEONE,patients);
        PatientTestsFragment fragment = new PatientTestsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            patientID = getArguments().getString(ARG_PATIENT_ID);
            theOne = getArguments().getParcelable(ARG_THEONE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        patientUtils = new PatientUtils();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_patient_tests, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.tests_recyclerview);
        patientTestAdapter = new PatientTestAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        recyclerView.setAdapter(patientTestAdapter);

        menuRed = (FloatingActionMenu) view.findViewById(R.id.fab_test);
        fab_test_add = (FloatingActionButton) view.findViewById(R.id.fab_test_add);
        //fab2 = (FloatingActionButton) v.findViewById(R.id.fab_treat_refresh);
        fab_test_add.setOnClickListener(this);
        //fab2.setOnClickListener(this);

        patientUtils.loadPatientTest(this,patientID);

        return view;
    }

    @Override
    public void onPatientTestLoaded(ArrayList<Test> testList) {
        patientTestAdapter.setTestList(testList);
        patientTestAdapter.notifyItemRangeChanged(0,testList.size());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    //floating button click listener
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_test_add:
                Intent intent = new Intent(getActivity(), AddTest.class);
                intent.putExtra("Patient", (Serializable) theOne);
                //intent.putParcelableArrayListExtra(PATIENT_LIST,patientList);
                startActivity(intent);
                Snackbar.make(v, "Add new", Snackbar.LENGTH_SHORT).show();
                menuRed.close(true);
                break;
//            case R.id.fab2:
//                patientUtils.loadPatientList(this);
//                menuRed.close(true);
//                break;
        }
    }
}
