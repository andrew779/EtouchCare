/**
 * TeamOne
 */
package com.app.etouchcare.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.etouchcare.R;
import com.app.etouchcare.adapters.PatientTestAdapter;
import com.app.etouchcare.callbacks.PatientLoadedListener.PatientTestLoadedListener;
import com.app.etouchcare.datamodel.Patients;
import com.app.etouchcare.datamodel.Test;
import com.app.etouchcare.extra.PatientUtils;
import com.app.etouchcare.extra.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PatientTestsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PatientTestsFragment extends Fragment implements PatientTestLoadedListener{
    private RecyclerView recyclerView;
    private PatientTestAdapter patientTestAdapter;
    private PatientUtils patientUtils;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String id;
    private Patients theOne;


    public PatientTestsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PatientTestsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PatientTestsFragment newInstance(String param1, Patients param2) {
        PatientTestsFragment fragment = new PatientTestsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putParcelable(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getString(ARG_PARAM1);
            theOne = getArguments().getParcelable(ARG_PARAM2);
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

        //floa


        patientUtils.loadPatientTest(this,id);

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
}
