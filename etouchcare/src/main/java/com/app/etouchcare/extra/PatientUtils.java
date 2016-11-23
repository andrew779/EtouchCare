package com.app.etouchcare.extra;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.app.etouchcare.datamodel.Patients;

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
    private static ArrayList<HashMap<String,String>> testList = new ArrayList<>();

    public static ArrayList<Patients> loadPatientList(RequestQueue requestQueue) {


        //creating a JSONObject request
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_LIST_ALL_PATIENTS, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                listPatients = parseJSONResponse(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Wenzhong", "ERR: " + error);
            }
        });
        requestQueue.add(request);
        return listPatients;
    }

    public static ArrayList<HashMap<String,String>> loadPatientTest (RequestQueue requestQueue,String id){


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_PATIENT_TEST + id, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                testList = parseTestJSONResponse(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Wenzhong", "ERR: " + error);
            }
        });
        requestQueue.add(request);

        return testList;
    }
}
