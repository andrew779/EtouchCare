package com.app.etouchcare.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.etouchcare.R;
import com.app.etouchcare.activity.AddTreatmentActivity;
import com.app.etouchcare.adapters.PatientTreatmentAdapter;
import com.app.etouchcare.callbacks.PatientLoadedListener;
import com.app.etouchcare.datamodel.Patients;
import com.app.etouchcare.datamodel.Treatments;
import com.app.etouchcare.extra.PatientUtils;
import com.app.etouchcare.extra.SimpleDividerItemDecoration;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PatientTreatmentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PatientTreatmentFragment extends Fragment implements PatientLoadedListener.PatientTreatmentLoadedListener, View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Patients theOne;
    private String id;
    private ArrayList<Treatments> treatList = new ArrayList<>();
    //UI
    private RecyclerView recyclerView;
    private PatientTreatmentAdapter adapter;
    private PatientUtils patientUtils;
    private FloatingActionMenu fabMenu;
    private FloatingActionButton fabAdd, fabRefresh;

    public PatientTreatmentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param id Parameter 1.
     * @param theOne Parameter 2.
     * @return A new instance of fragment PatientTreatmentFragment.
     */

    public static PatientTreatmentFragment newInstance(String id, Patients theOne) {
        PatientTreatmentFragment fragment = new PatientTreatmentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, id);
        args.putParcelable(ARG_PARAM2, theOne);
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



        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_patient_treatment, container, false);

        patientUtils = new PatientUtils();
        recyclerView = (RecyclerView) v.findViewById(R.id.treatment_recyclerview);
        adapter = new PatientTreatmentAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        recyclerView.setAdapter(adapter);

        //floating button
        fabMenu = (FloatingActionMenu) v.findViewById(R.id.fab_treat);
        fabAdd = (FloatingActionButton) v.findViewById(R.id.fab_treat_add);
        fabRefresh = (FloatingActionButton) v.findViewById(R.id.fab_treat_refresh);
        fabAdd.setOnClickListener(this);
        fabRefresh.setOnClickListener(this);


        patientUtils.loadPatientTreatment(this,id);

        return v;
    }

    @Override
    public void onPatientTreatmentLoaded(ArrayList<Treatments> treatmentList) {
        this.treatList = treatmentList;
        adapter.setTreatList(treatmentList);
        adapter.notifyItemRangeChanged(0,treatmentList.size());

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_treat_add:
                Intent intent = new Intent(getActivity(), AddTreatmentActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
                break;
            case R.id.fab_treat_refresh:
                patientUtils.loadPatientTreatment(this,id);
                break;
        }
    }
}
