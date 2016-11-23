package com.app.etouchcare.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.etouchcare.R;
import com.app.etouchcare.callbacks.PatientTestLoadedListener;
import com.app.etouchcare.tasks.TaskLoadPatientTest;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PatientTestsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PatientTestsFragment extends Fragment implements PatientTestLoadedListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


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
    public static PatientTestsFragment newInstance(String param1, String param2) {
        PatientTestsFragment fragment = new PatientTestsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_patient_tests, container, false);
        Button button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TaskLoadPatientTest(PatientTestsFragment.this,"582de16906ee352605f29397").execute();
            }
        });
        return view;
    }

    @Override
    public void onPatientTestLoaded(ArrayList<HashMap<String, String>> testList) {
        Log.d("wenzhong","TestResult:\n"+testList.toString());
    }
}
