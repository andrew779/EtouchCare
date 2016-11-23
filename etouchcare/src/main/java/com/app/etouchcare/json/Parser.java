package com.app.etouchcare.json;

import android.util.Log;

import com.app.etouchcare.callbacks.PatientLoadedListener.*;
import com.app.etouchcare.datamodel.Patients;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static com.app.etouchcare.extra.Keys.EndPointPatientList.*;
import static com.app.etouchcare.extra.Keys.EndPointPatientTest.*;
import static com.app.etouchcare.extra.Keys.EndPointPatientDiagnosis.*;
/**
 * Created by wenzhongzheng on 2016-11-20.
 */

public class Parser {
    //parsing json data into Patients data model
    public static void parseJSONResponse(JSONObject response, PatientListLoadedListener mPatientListLoaded){
        ArrayList<Patients> listPatients = new ArrayList<>();
        if (response==null||response.length()==0){
            return;
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

        } catch (JSONException e) {
            e.printStackTrace();

        }
        if (mPatientListLoaded != null) mPatientListLoaded.onPatientListLoaded(listPatients);

    }

    public static void parseTestJSONResponse(JSONObject response, PatientTestLoadedListener patientTestLoadedListener){
        ArrayList<HashMap<String,String>> listTest = new ArrayList<>();
        if (response==null||response.length()==0){
            return;
        }
        try {
            JSONArray arrayTests = response.getJSONArray(KEY_TEST_ROOT);
            for (int i=0;i<arrayTests.length();i++){
                JSONObject currentPatient = arrayTests.getJSONObject(i);
                //get current patient id
                String id = currentPatient.getString(KEY_TEST_ID);
                //get current patient name
                String name = currentPatient.getString(KEY_TEST_NAME);
                //get current patient diagnosis
                String date = currentPatient.getString(KEY_TEST_DATE);
                //get current patient diagnosis detail
                String patientID = currentPatient.getString(KEY_TEST_PATIENT_ID);
                //get current patient room
                String testID = currentPatient.getString(KEY_TEST_TESTID);

                String result = currentPatient.getString(KEY_TEST_RESULT);
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(KEY_TEST_ID,id);
                hashMap.put(KEY_TEST_NAME,name);
                hashMap.put(KEY_TEST_DATE,date);
                hashMap.put(KEY_TEST_PATIENT_ID,patientID);
                hashMap.put(KEY_TEST_TESTID,testID);
                hashMap.put(KEY_TEST_RESULT,result);
                listTest.add(hashMap);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (patientTestLoadedListener != null) patientTestLoadedListener.onPatientTestLoaded(listTest);
    }

    public static void parseDiagnosisJSONResponse(JSONObject response, PatientDiagnosisLoadedListener patientDiagnosisLoadedListener){
        ArrayList<HashMap<String,String>> listTest = new ArrayList<>();
        if (response==null||response.length()==0){
            return;
        }
        try {
            JSONArray arrayTests = response.getJSONArray(KEY_DIAG_ROOT);
            for (int i=0;i<arrayTests.length();i++){
                JSONObject currentPatient = arrayTests.getJSONObject(i);
                //get current patient id
                String id = currentPatient.getString(KEY_DIAG_ID);
                //get current patient name
                String description = currentPatient.getString(KEY_DIAG_DESCRPTION);
                //get current patient diagnosis
                String date = currentPatient.getString(KEY_DIAG_DATE);
                //get current patient diagnosis detail
                String patientID = currentPatient.getString(KEY_DIAG_PATIENT_ID);


                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(KEY_DIAG_ID,id);
                hashMap.put(KEY_DIAG_DESCRPTION,description);
                hashMap.put(KEY_DIAG_DATE,date);
                hashMap.put(KEY_DIAG_PATIENT_ID,patientID);

                listTest.add(hashMap);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (patientDiagnosisLoadedListener != null){
            patientDiagnosisLoadedListener.onPatientDiagnosisLoaded(listTest);
            Log.d("Parser","Check parsed list: \n"+listTest.toString());
        }

        else
            Log.d("Parser","Null diagnosisloadedListener");
    }
}
