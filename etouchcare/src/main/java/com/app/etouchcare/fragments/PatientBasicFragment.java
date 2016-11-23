package com.app.etouchcare.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.etouchcare.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PatientBasicFragment extends Fragment {

    public static final String ARG_PATIENT_ID = "ARG_PATIENT_ID";

    private String patientID;

    public static PatientBasicFragment newInstance(String id) {
        Bundle args = new Bundle();
        args.putString(ARG_PATIENT_ID, id);
        PatientBasicFragment fragment = new PatientBasicFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        patientID = getArguments().getString(ARG_PATIENT_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patient_basic, container, false);
        TextView tvID = (TextView) view.findViewById(R.id.basic_id);
        tvID.setText(patientID);

        return view;
    }
}

