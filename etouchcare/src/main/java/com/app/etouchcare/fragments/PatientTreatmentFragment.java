/**
 * TeamOne
 */
package com.app.etouchcare.fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.etouchcare.R;
import com.app.etouchcare.activity.AddTreatmentActivity;
import com.app.etouchcare.adapters.PatientTreatmentAdapter;
import com.app.etouchcare.callbacks.PatientLoadedListener;
import com.app.etouchcare.datamodel.Patients;
import com.app.etouchcare.datamodel.Treatments;
import com.app.etouchcare.extra.PatientUtils;
import com.app.etouchcare.extra.RecyclerTouchListener;
import com.app.etouchcare.extra.SimpleDividerItemDecoration;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

import static com.app.etouchcare.extra.mUrls.getAllPatients.URL_PATIENT_DIAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PatientTreatmentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PatientTreatmentFragment extends Fragment implements PatientLoadedListener.PatientTreatmentLoadedListener, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, PatientLoadedListener.RecordDeletedListener {
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
    private SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionMenu fabMenu;
    private FloatingActionButton fabAdd;

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

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view,final int position) {
                TextView textView = (TextView) view.findViewById(R.id.treat_id);
                final String finalStr = textView.getText().toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                // Add the buttons
                builder.setTitle("DELETE");
                builder.setMessage("Do you want to delete the record");

                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        patientUtils.deletePatientList(PatientTreatmentFragment.this,URL_PATIENT_DIAG + finalStr,position);
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
                // Set other dialog properties


                // Create the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.setCancelable(true);
                dialog.show();
            }
        }));

        //swipe layout
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.treatment_refreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);

        //floating button
        fabMenu = (FloatingActionMenu) v.findViewById(R.id.fab_treat);
        fabAdd = (FloatingActionButton) v.findViewById(R.id.fab_treat_add);
        fabAdd.setOnClickListener(this);



        patientUtils.loadPatientTreatment(this,id);

        return v;
    }

    @Override
    public void onPatientTreatmentLoaded(ArrayList<Treatments> treatmentList) {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }

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

        }
    }

    @Override
    public void onRefresh() {
        patientUtils.loadPatientTreatment(this,id);
    }

    @Override
    public void onRecordDeleted(int position) {
        treatList.remove(position);
        adapter.notifyItemRemoved(position);
    }
}
