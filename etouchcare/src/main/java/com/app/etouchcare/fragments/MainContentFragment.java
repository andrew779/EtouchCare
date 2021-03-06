/**
 * TeamOne
 */
package com.app.etouchcare.fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.etouchcare.R;
import com.app.etouchcare.activity.AddPatient;
import com.app.etouchcare.activity.PatientDetailActivity;
import com.app.etouchcare.adapters.PatientListAdapter;
import com.app.etouchcare.callbacks.PatientLoadedListener;
import com.app.etouchcare.callbacks.PatientLoadedListener.PatientListLoadedListener;
import com.app.etouchcare.datamodel.Patients;
import com.app.etouchcare.extra.PatientUtils;
import com.app.etouchcare.extra.RecyclerTouchListener;
import com.app.etouchcare.extra.SimpleDividerItemDecoration;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import java.util.ArrayList;
import java.util.Iterator;

import static com.app.etouchcare.extra.mUrls.getAllPatients.URL_LIST_ALL_PATIENTS;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainContentFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, PatientListLoadedListener, View.OnClickListener ,PatientLoadedListener.RecordDeletedListener{

    private PatientListAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionMenu menuRed;
    private FloatingActionButton fab1, fab2;
    private OnFetchIDListener mListener;
    private ArrayList<Patients> patientList = new ArrayList<>();
    private ArrayList<Patients> filteredList = new ArrayList<>();
    private PatientUtils patientUtils;


    public static final String STATE_PATIENTS = "state_patients";
    public static final String PATIENT_LIST = "patient_list";
    private EditText search;


    public MainContentFragment() {
        // Required empty public constructor
    }



    public interface OnFetchIDListener {
        void onFetchID(String id);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_content, container, false);
        patientUtils = new PatientUtils();
        //refresh layout object
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.patientlist_refreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);

        //search
        search = (EditText) view.findViewById(R.id.patientlist_search);
        addTextListener();


        //floating button
        menuRed = (FloatingActionMenu) view.findViewById(R.id.menu_red);
        fab1 = (FloatingActionButton) view.findViewById(R.id.fab1);
//        fab2 = (FloatingActionButton) view.findViewById(R.id.fab2);
        fab1.setOnClickListener(this);
//        fab2.setOnClickListener(this);

        //recyclerview object
        recyclerView = (RecyclerView) view.findViewById(R.id.patientlist_recyclerview);
        adapter = new PatientListAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                TextView tvID = (TextView) view.findViewById(R.id.patientlist_row_id);
                String str = tvID.getText().toString();
                str = str.substring(str.indexOf(" ") + 1);
                if (mListener != null) mListener.onFetchID(str);
                Intent intent = new Intent(getActivity(), PatientDetailActivity.class);
                intent.putExtra("id", str);
                intent.putParcelableArrayListExtra(PATIENT_LIST, patientList);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, final int position) {
                TextView tvID = (TextView) view.findViewById(R.id.patientlist_row_id);
                String str = tvID.getText().toString();
                str = str.substring(str.indexOf(" ") + 1);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                // Add the buttons
                builder.setTitle("DELETE");
                builder.setMessage("Do you want to delete the record");
                final String finalStr = str;
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        patientUtils.deletePatientList(MainContentFragment.this,URL_LIST_ALL_PATIENTS + finalStr,position);

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


        patientUtils.loadPatientList(this);

        return view;
    }


    @Override
    public void onRefresh() {
        patientUtils.loadPatientList(this);
    }

    @Override
    public void onPatientListLoaded(ArrayList<Patients> patientList) {

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        this.patientList = patientList;
        filteredList = patientList;
        adapter.setPatientList(filteredList);
        adapter.notifyItemRangeChanged(0, filteredList.size() - 1);

    }

    @Override
    public void onRecordDeleted(int position) {

        Iterator<Patients> iterator = patientList.iterator();
        while (iterator.hasNext()){
            Patients one = iterator.next();
            if(filteredList.get(position).getId().equals(one.getId())){
                iterator.remove();
            }
        }
        filteredList.remove(position);

        Log.d("after delete",patientList.toString());
        adapter.notifyItemRemoved(position);
        Toast.makeText(getActivity(),"DELETED",Toast.LENGTH_SHORT).show();
    }

    //floating button click listener
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab1:
                Intent intent = new Intent(getActivity(), AddPatient.class);
                //intent.putExtra("id",str);
                //intent.putParcelableArrayListExtra(PATIENT_LIST,patientList);
                startActivity(intent);

                Snackbar.make(v, "Add new", Snackbar.LENGTH_SHORT).show();
                menuRed.close(true);
                break;

        }
    }

    public void onButtonPressed(String id) {
        if (mListener != null) {
            mListener.onFetchID(id);
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
        if (context instanceof OnFetchIDListener) {
            mListener = (OnFetchIDListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public void addTextListener(){
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                s = s.toString().toLowerCase();

                filteredList = new ArrayList<>();

                for (int i = 0; i < patientList.size(); i++) {

                    final String text = patientList.get(i).getName().toLowerCase();
                    if (text.contains(s)) {

                        filteredList.add(patientList.get(i));
                    }
                }
                adapter.setPatientList(filteredList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}
