package com.app.etouchcare.json;

import android.util.Log;

import com.app.etouchcare.datamodel.Patients;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static com.app.etouchcare.extra.Keys.EndPointPatientList.*;
import static com.app.etouchcare.extra.Keys.EndPointPatientTest.*;
/**
 * Created by wenzhongzheng on 2016-11-20.
 */

public class Parser {
    //parsing json data into Patients data model
    public static ArrayList<Patients> parseJSONResponse(JSONObject response){
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

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listPatients;
    }

    public static ArrayList<HashMap<String,String>> parseTestJSONResponse(JSONObject response){
        ArrayList<HashMap<String,String>> listTest = new ArrayList<>();
        if (response==null||response.length()==0){
            return null;
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
        return listTest;
    }
}
