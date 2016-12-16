/**
 * TeamOne
 */
package com.app.etouchcare.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    private TextView tvID,tvName,tvRoom,tvAge,tvEmail,tvPhone,tvAddress,tvEmName,tvEmPhone;

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
        tvAge.setText(theOne.getAge());

        tvRoom = (TextView) view.findViewById(R.id.basic_room);
        tvRoom.setText(theOne.getRoom());

        tvEmail = (TextView) view.findViewById(R.id.basic_email);
        tvEmail.setText(theOne.getEmail());

        tvPhone = (TextView) view.findViewById(R.id.basic_phone);
        tvPhone.setText(theOne.getPhone());

        tvAddress = (TextView) view.findViewById(R.id.basic_address);
        tvAddress.setText(theOne.getAddress());
        //emergency contact
        tvEmName = (TextView) view.findViewById(R.id.basic_name_em);
        tvEmName.setText(theOne.getEmergencyName());

        tvEmPhone = (TextView) view.findViewById(R.id.basic_phone_em);
        tvEmPhone.setText(theOne.getEmergencyPhone());

        ImageView imageView = (ImageView) view.findViewById(R.id.basic_img);
        String gender = theOne.getGender();
        if (gender.equalsIgnoreCase("male")){
            imageView.setImageResource(R.drawable.male);
        }else if (gender.equalsIgnoreCase("female")){
            imageView.setImageResource(R.drawable.female);
        }else{
            imageView.setImageResource(R.drawable.na);
        }

        return view;
    }
}

