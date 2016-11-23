package com.app.etouchcare.tasks;

import android.os.AsyncTask;

import com.android.volley.RequestQueue;
import com.app.etouchcare.callbacks.PatientTestLoadedListener;
import com.app.etouchcare.extra.PatientUtils;
import com.app.etouchcare.network.VolleySingleton;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by wenzhongzheng on 2016-11-22.
 */

public class TaskLoadPatientTest extends AsyncTask<Void,Void,ArrayList<HashMap<String,String>>> {
    private PatientTestLoadedListener myComponent;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    private String id;

    public TaskLoadPatientTest(PatientTestLoadedListener myComponent,String id) {
        this.myComponent = myComponent;
        this.id = id;
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getmRequestQueue();
    }

    @Override
    protected ArrayList<HashMap<String, String>> doInBackground(Void... params) {
        arrayList = PatientUtils.loadPatientTest(requestQueue,id);

        return arrayList;
    }

    @Override
    protected void onPostExecute(ArrayList<HashMap<String, String>> testList) {
        if(myComponent != null){
            myComponent.onPatientTestLoaded(testList);
        }
    }
}
