/**
 * TeamOne
 */
package com.app.etouchcare.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.etouchcare.R;
import com.app.etouchcare.activity.AddSympton;
import com.app.etouchcare.activity.AddTest;
import com.app.etouchcare.adapters.PatientDiagnosisAdapter;
import com.app.etouchcare.callbacks.PatientLoadedListener.PatientDiagnosisLoadedListener;
import com.app.etouchcare.datamodel.Diagnosis;
import com.app.etouchcare.datamodel.Patients;
import com.app.etouchcare.extra.PatientUtils;
import com.app.etouchcare.extra.SimpleDividerItemDecoration;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PatientDiagnosisFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PatientDiagnosisFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PatientDiagnosisFragment extends Fragment implements PatientDiagnosisLoadedListener, View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String id;
    private Patients theOne;

    private TextView tvDiagnosis;
    private RecyclerView recyclerView;
    private PatientDiagnosisAdapter adapter;
    private OnFragmentInteractionListener mListener;

    private PatientUtils patientUtils;
    private FloatingActionMenu fab_dia;
    private FloatingActionButton fab_dia_add;
    private SwipeRefreshLayout swipeRefreshLayout;

    public PatientDiagnosisFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PatientDiagnosisFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PatientDiagnosisFragment newInstance(String param1, Patients param2) {
        PatientDiagnosisFragment fragment = new PatientDiagnosisFragment();
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_patient_diagnosis, container, false);
        patientUtils = new PatientUtils();
        tvDiagnosis = (TextView) v.findViewById(R.id.diagnosis_diag);
        tvDiagnosis.setText(theOne.getDiagnosis());
        recyclerView = (RecyclerView) v.findViewById(R.id.diagnosis_sym_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new PatientDiagnosisAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));

        fab_dia = (FloatingActionMenu) v.findViewById(R.id.fab_test); //fab_dia_add
        fab_dia_add = (FloatingActionButton) v.findViewById(R.id.fab_dia_add);
        //fab2 = (FloatingActionButton) v.findViewById(R.id.fab_treat_refresh);
        fab_dia_add.setOnClickListener(this);

        patientUtils.loadPatientDiagnosis(this,id);

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
    }

    @Override
    public void onPatientDiagnosisLoaded(ArrayList<Diagnosis> diagnosisList) {
        adapter.setDiagnosisList(diagnosisList);
        adapter.notifyItemRangeChanged(0,diagnosisList.size());
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


//    @Override
//    public void setOnClickListener(final View.OnClickListener l) {
//        super.setOnClickListener(l);
//        mClickListener = l;
//        View label = (View) getTag(com.github.clans.fab.R.id.fab_label);
//        if (label != null) {
//            label.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mClickListener != null) {
//                        mClickListener.onClick(FloatingActionButton.this);
//                    }
//                }
//            });
//        }
//    }

    //floating button click listener
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_dia_add:
                Intent intent = new Intent(getActivity(), AddSympton.class);
                intent.putExtra("Patient", (Parcelable) theOne);
                //intent.putExtra("Patient", (Serializable) theOne);
                //intent.putParcelableArrayListExtra(PATIENT_LIST,patientList);
                startActivity(intent);
                Snackbar.make(v, "Add new", Snackbar.LENGTH_SHORT).show();
                //fab_dia.close(true);
                break;
//            case R.id.fab2:
//                patientUtils.loadPatientList(this);
//                menuRed.close(true);
//                break;
        }
    }
}
