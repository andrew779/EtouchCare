/**
 * TeamOne
 */
package com.app.etouchcare.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.etouchcare.R;
import com.app.etouchcare.datamodel.Patients;


/**
 * A simple {@link Fragment} subclass.
 */
public class PatientBasicFragment extends Fragment {

    private static final String ARG_PATIENT_ID = "ARG_PATIENT_ID";
    private static final String ARG_THEONE = "ARG_THEONE";
    private String patientID;
    private Patients theOne;
    private TextView tvID,tvName,tvRoom,tvAge,tvEmail,tvPhone,tvAddress,tvEmName,tvEmRelation,tvEmPhone;

    public static PatientBasicFragment newInstance(String id,Patients patients) {
        Bundle args = new Bundle();
        args.putString(ARG_PATIENT_ID, id);
        args.putParcelable(ARG_THEONE,patients);
        PatientBasicFragment fragment = new PatientBasicFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        patientID = getArguments().getString(ARG_PATIENT_ID);
        theOne = getArguments().getParcelable(ARG_THEONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patient_basic, container, false);
        tvID = (TextView) view.findViewById(R.id.basic_id);
        tvID.setText(patientID);
        tvName = (TextView) view.findViewById(R.id.basic_name);
        tvName.setText(theOne.getName());
        tvAge = (TextView) view.findViewById(R.id.basic_age);
        tvRoom = (TextView) view.findViewById(R.id.basic_room);
        tvRoom.setText(theOne.getRoom());


        return view;
    }
}

