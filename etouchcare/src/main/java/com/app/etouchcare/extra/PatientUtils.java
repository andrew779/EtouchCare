package com.app.etouchcare.extra;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.app.etouchcare.callbacks.PatientLoadedListener.*;
import com.app.etouchcare.datamodel.Patients;
import com.app.etouchcare.network.VolleySingleton;

import org.json.JSONObject;

import java.util.ArrayList;
<<<<<<< HEAD

import static com.app.etouchcare.extra.mUrls.getAllPatients.URL_LIST_ALL_PATIENTS;
import static com.app.etouchcare.json.Parser.parseJSONResponse;
=======
import java.util.HashMap;

import static com.app.etouchcare.extra.mUrls.getAllPatients.URL_LIST_ALL_PATIENTS;
import static com.app.etouchcare.extra.mUrls.getAllPatients.URL_PATIENT_DIAG;
import static com.app.etouchcare.extra.mUrls.getAllPatients.URL_PATIENT_TEST;
import static com.app.etouchcare.json.Parser.parseDiagnosisJSONResponse;
import static com.app.etouchcare.json.Parser.parseJSONResponse;
import static com.app.etouchcare.json.Parser.parseTestJSONResponse;
>>>>>>> f7cf8675e1a094a013de591979dfd9bdb71a1454

/**
 * Created by wenzhongzheng on 2016-11-20.
 */

public class PatientUtils {
    private static ArrayList<Patients> listPatients = new ArrayList<>();
<<<<<<< HEAD
    private static ArrayList<HashMap<String, String>> testList = new ArrayList<>();
    VolleySingleton volleySingleton;
    RequestQueue requestQueue;
=======
<<<<<<< HEAD
=======
    private static ArrayList<HashMap<String,String>> testList = new ArrayList<>();
>>>>>>> f7cf8675e1a094a013de591979dfd9bdb71a1454
>>>>>>> b4dece0f13f164ec703afb21d910522b9c1665ec

    public PatientUtils() {
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getmRequestQueue();
    }


    public void loadPatientList(final PatientListLoadedListener mPatientListLoaded) {


        //creating a JSONObject request
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_LIST_ALL_PATIENTS, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
<<<<<<< HEAD
                parseJSONResponse(response, mPatientListLoaded);
=======
<<<<<<< HEAD
//                Log.d("Wenzhong","response: "+response);
=======
>>>>>>> f7cf8675e1a094a013de591979dfd9bdb71a1454
                listPatients = parseJSONResponse(response);

>>>>>>> b4dece0f13f164ec703afb21d910522b9c1665ec
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Wenzhong", "ERR: " + error);
            }
        });
        requestQueue.add(request);
    }
<<<<<<< HEAD
=======

    public void loadPatientTest(final PatientTestLoadedListener patientTestLoadedListener, String id) {

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

    public void loadPatientDiagnosis(final PatientDiagnosisLoadedListener patientDiagnosisLoadedListener,String id){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_PATIENT_DIAG+id, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                parseDiagnosisJSONResponse(response, patientDiagnosisLoadedListener);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Wenzhong", "ERR: " + error);
            }
        });
        requestQueue.add(request);
    }
>>>>>>> f7cf8675e1a094a013de591979dfd9bdb71a1454
}
