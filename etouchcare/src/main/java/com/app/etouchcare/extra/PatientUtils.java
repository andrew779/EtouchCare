package com.app.etouchcare.extra;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.app.etouchcare.callbacks.PatientListLoadedListener;
import com.app.etouchcare.callbacks.PatientTestLoadedListener;
import com.app.etouchcare.datamodel.Patients;
import com.app.etouchcare.network.VolleySingleton;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static com.app.etouchcare.extra.mUrls.getAllPatients.URL_LIST_ALL_PATIENTS;
import static com.app.etouchcare.extra.mUrls.getAllPatients.URL_PATIENT_TEST;
import static com.app.etouchcare.json.Parser.parseJSONResponse;
import static com.app.etouchcare.json.Parser.parseTestJSONResponse;

/**
 * Created by wenzhongzheng on 2016-11-20.
 */

public class PatientUtils {
    private static ArrayList<Patients> listPatients = new ArrayList<>();
    private static ArrayList<HashMap<String, String>> testList = new ArrayList<>();
    VolleySingleton volleySingleton;
    RequestQueue requestQueue;

    public PatientUtils() {
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getmRequestQueue();
    }


    public void loadPatientList(final PatientListLoadedListener mPatientListLoaded) {


        //creating a JSONObject request
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_LIST_ALL_PATIENTS, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                parseJSONResponse(response, mPatientListLoaded);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Wenzhong", "ERR: " + error);
            }
        });
        requestQueue.add(request);
    }

    public void loadPatientTest(final PatientTestLoadedListener patientTestLoadedListener, String id) {
        Log.d("PatientUtils", String.format("%s%s", URL_PATIENT_TEST, id));

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_PATIENT_TEST+id, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                parseTestJSONResponse(response, patientTestLoadedListener);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Wenzhong", "ERR: " + error);
            }
        });
        requestQueue.add(request);

    }
}
