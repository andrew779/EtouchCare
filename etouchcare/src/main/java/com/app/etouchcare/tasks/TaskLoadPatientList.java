<<<<<<< HEAD
//package com.app.etouchcare.tasks;
//
//import android.os.AsyncTask;
//import android.util.Log;
//
//import com.android.volley.RequestQueue;
//import com.app.etouchcare.callbacks.PatientListLoadedListener;
//import com.app.etouchcare.datamodel.Patients;
//import com.app.etouchcare.extra.PatientUtils;
//import com.app.etouchcare.network.VolleySingleton;
//
//import java.util.ArrayList;
//
///**
// * Created by wenzhongzheng on 2016-11-20.
// */
//
//public class TaskLoadPatientList extends AsyncTask<Void, Void, ArrayList<Patients>> {
//    private PatientListLoadedListener myComponent;
//    private VolleySingleton volleySingleton;
//    private RequestQueue requestQueue;
//
//
//    public TaskLoadPatientList(PatientListLoadedListener myComponent) {
//        this.myComponent = myComponent;
//        volleySingleton = VolleySingleton.getInstance();
//        requestQueue = volleySingleton.getmRequestQueue();
//
//        ArrayList<Patients> patientList = PatientUtils.loadPatientList(requestQueue);
//        myComponent.onPatientListLoaded(patientList);
//    }
//
//    @Override
//    protected ArrayList<Patients> doInBackground(Void... params) {
//        ArrayList<Patients> patientList = PatientUtils.loadPatientListTest(requestQueue);
//
//        return patientList;
//    }
//
//    @Override
//    protected void onPostExecute(ArrayList<Patients> patients) {
//        if(myComponent != null){
//            myComponent.onPatientListLoaded(patients);
//        }
//
//        //Log.d("wenzhong","TaskLoadPatientList onPostExecute: "+patients);
//
//    }
//}
=======
package com.app.etouchcare.tasks;

import android.os.AsyncTask;
<<<<<<< HEAD
=======
import android.util.Log;
>>>>>>> f7cf8675e1a094a013de591979dfd9bdb71a1454

import com.android.volley.RequestQueue;
import com.app.etouchcare.callbacks.PatientListLoadedListener;
import com.app.etouchcare.datamodel.Patients;
import com.app.etouchcare.extra.PatientUtils;
import com.app.etouchcare.network.VolleySingleton;

import java.util.ArrayList;

/**
 * Created by wenzhongzheng on 2016-11-20.
 */

public class TaskLoadPatientList extends AsyncTask<Void, Void, ArrayList<Patients>> {
    private PatientListLoadedListener myComponent;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;


    public TaskLoadPatientList(PatientListLoadedListener myComponent) {
        this.myComponent = myComponent;
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getmRequestQueue();
<<<<<<< HEAD
=======

        ArrayList<Patients> patientList = PatientUtils.loadPatientList(requestQueue);
        myComponent.onPatientListLoaded(patientList);
>>>>>>> f7cf8675e1a094a013de591979dfd9bdb71a1454
    }

    @Override
    protected ArrayList<Patients> doInBackground(Void... params) {
        ArrayList<Patients> patientList = PatientUtils.loadPatientList(requestQueue);

        return patientList;
    }

    @Override
    protected void onPostExecute(ArrayList<Patients> patients) {
        if(myComponent != null){
            myComponent.onPatientListLoaded(patients);
        }
<<<<<<< HEAD
=======
        Log.d("wenzhong","TaskLoadPatientList onPostExecute: "+patients);

>>>>>>> f7cf8675e1a094a013de591979dfd9bdb71a1454
    }
}
>>>>>>> b4dece0f13f164ec703afb21d910522b9c1665ec
