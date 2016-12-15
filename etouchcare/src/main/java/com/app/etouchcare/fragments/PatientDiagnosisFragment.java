/**
 * TeamOne
 */
package com.app.etouchcare.fragments;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.etouchcare.R;
import com.app.etouchcare.adapters.PatientDiagnosisAdapter;
import com.app.etouchcare.callbacks.PatientLoadedListener;
import com.app.etouchcare.callbacks.PatientLoadedListener.PatientDiagnosisLoadedListener;
import com.app.etouchcare.datamodel.Diagnosis;
import com.app.etouchcare.datamodel.Patients;
import com.app.etouchcare.extra.PatientUtils;
import com.app.etouchcare.extra.RecyclerTouchListener;
import com.app.etouchcare.extra.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;

import static com.app.etouchcare.extra.mUrls.getAllPatients.URL_PATIENT_DIAG;
import static com.app.etouchcare.extra.mUrls.getAllPatients.URL_PATIENT_TEST;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PatientDiagnosisFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PatientDiagnosisFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PatientDiagnosisFragment extends Fragment implements PatientDiagnosisLoadedListener, SwipeRefreshLayout.OnRefreshListener, PatientLoadedListener.RecordDeletedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String id;
    private Patients theOne;
    private ArrayList<Diagnosis> diagnosisList = new ArrayList<>();

    private TextView tvDiagnosis;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;



    private PatientDiagnosisAdapter adapter;
    private PatientUtils patientUtils;
    private OnFragmentInteractionListener mListener;

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

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, final int position) {
                TextView textView = (TextView) view.findViewById(R.id.diagnosis_id);
                final String finalStr = textView.getText().toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                // Add the buttons
                builder.setTitle("DELETE");
                builder.setMessage("Do you want to delete the record");

                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        patientUtils.deletePatientList(PatientDiagnosisFragment.this,URL_PATIENT_DIAG + finalStr,position);
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

        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.diagnosis_refreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);

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
    public void onPatientDiagnosisLoaded(ArrayList<Diagnosis> diagnosisList) {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        this.diagnosisList = diagnosisList;
        adapter.setDiagnosisList(diagnosisList);
        adapter.notifyItemRangeChanged(0,diagnosisList.size());
    }

    @Override
    public void onRefresh() {
        patientUtils.loadPatientDiagnosis(this,id);
    }

    @Override
    public void onRecordDeleted(int position) {
        diagnosisList.remove(position);
        adapter.notifyItemRemoved(position);

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
}
