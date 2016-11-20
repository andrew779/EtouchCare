package com.app.etouchcare.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.etouchcare.adapters.PatientListAdapter;
import com.app.etouchcare.R;
import com.app.etouchcare.callbacks.PatientListLoadedListener;
import com.app.etouchcare.datamodel.Patients;
import com.app.etouchcare.tasks.TaskLoadPatientList;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainContentFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, PatientListLoadedListener {

    PatientListAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<Patients>  listPatients = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;

//    public static final String URL_LIST_ALL_PATIENTS = "http://mapd2016.herokuapp.com/";
    public static final String STATE_PATIENTS = "state_patients";

    public MainContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_content, container, false);
        //refresh layout object
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.patientlist_refreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);

        //recyclerview object
        recyclerView = (RecyclerView) view.findViewById(R.id.patientlist_recyclerview);
        adapter = new PatientListAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
//        if (savedInstanceState != null){
//            listPatients = savedInstanceState.getParcelableArrayList(STATE_PATIENTS);
//        }


        new TaskLoadPatientList(this).execute();


        return view;
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putParcelableArrayList(STATE_PATIENTS, listPatients);
//    }

 /*   public void sendJsonRequest(){
        //get volley RequestQueue object in order to execute request later
        RequestQueue requestQueue = VolleySingleton.getInstance().getmRequestQueue();

        //creating a JSONObject request
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_LIST_ALL_PATIENTS, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
//                Log.d("Wenzhong","response: "+response);
                listPatients = parseJSONResponse(response);
                adapter.setPatientList(listPatients);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Wenzhong","ERR: "+error);
            }
        });
        requestQueue.add(request);

    }

    //parsing json data into Patients data model
    private ArrayList<Patients> parseJSONResponse(JSONObject response){
        ArrayList<Patients> listPatients = new ArrayList<>();
        if (response==null||response.length()==0){
            return null;
        }
        try {
            JSONArray arrayPatients = response.getJSONArray(KEY_PATIENTS);
            for (int i=0;i<arrayPatients.length();i++){
                JSONObject currentPatient = arrayPatients.getJSONObject(i);
                //get current patient id
                String id = currentPatient.getString(KEY_ID);
                //get current patient name
                String name = currentPatient.getString(KEY_NAME);
                //get current patient diagnosis
                String diagnosis = currentPatient.getString(KEY_DIAGNOSIS);
                //get current patient diagnosis detail
                String diagnosisDetail = currentPatient.getString(KEY_DIAGNOSIS_DETAIL);
                //get current patient room
                String room = currentPatient.getString(KEY_ROOM);
                //get current patient condition
                String condition = currentPatient.getString(KEY_CONDITION);

                Patients patients = new Patients();
                patients.setpName(name);
                patients.setId(id);
                patients.setDiagnosis(diagnosis);
                patients.setDiagnosisDetails(diagnosisDetail);
                patients.setRoom(room);
                patients.setCondition(condition);
                listPatients.add(patients);
            }
            Log.d("wenzhong",listPatients.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listPatients;
    }*/

    @Override
    public void onRefresh() {
        new TaskLoadPatientList(this).execute();
    }

    @Override
    public void onPatientListLoaded(ArrayList<Patients> patientList) {
        if(swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
        adapter.setPatientList(patientList);
    }
}
