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
<<<<<<< HEAD

import static com.app.etouchcare.extra.mUrls.getAllPatients.URL_LIST_ALL_PATIENTS;
import static com.app.etouchcare.json.Parser.parseJSONResponse;
=======
import java.util.HashMap;

import static com.app.etouchcare.extra.mUrls.getAllPatients.URL_LIST_ALL_PATIENTS;
import static com.app.etouchcare.extra.mUrls.getAllPatients.URL_PATIENT_TEST;
import static com.app.etouchcare.json.Parser.parseJSONResponse;
import static com.app.etouchcare.json.Parser.parseTestJSONResponse;
>>>>>>> f7cf8675e1a094a013de591979dfd9bdb71a1454

/**
 * Created by wenzhongzheng on 2016-11-20.
 */

public class PatientUtils {
    private static ArrayList<Patients> listPatients = new ArrayList<>();
<<<<<<< HEAD
=======
    private static ArrayList<HashMap<String,String>> testList = new ArrayList<>();
>>>>>>> f7cf8675e1a094a013de591979dfd9bdb71a1454

    public static ArrayList<Patients> loadPatientList(RequestQueue requestQueue) {


        //creating a JSONObject request
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_LIST_ALL_PATIENTS, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
<<<<<<< HEAD
//                Log.d("Wenzhong","response: "+response);
=======
>>>>>>> f7cf8675e1a094a013de591979dfd9bdb71a1454
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
<<<<<<< HEAD
=======

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
>>>>>>> f7cf8675e1a094a013de591979dfd9bdb71a1454
}
