/**
 * TeamOne
 */
package com.app.etouchcare.json;

import android.util.Log;

import com.app.etouchcare.callbacks.PatientLoadedListener.*;
import com.app.etouchcare.datamodel.Diagnosis;
import com.app.etouchcare.datamodel.Patients;
import com.app.etouchcare.datamodel.Test;
import com.app.etouchcare.datamodel.Treatments;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.app.etouchcare.extra.Keys.EndPointPatientList.KEY_ADDRESS;
import static com.app.etouchcare.extra.Keys.EndPointPatientList.KEY_AGE;
import static com.app.etouchcare.extra.Keys.EndPointPatientList.KEY_CONDITION;
import static com.app.etouchcare.extra.Keys.EndPointPatientList.KEY_DIAGNOSIS;
import static com.app.etouchcare.extra.Keys.EndPointPatientList.KEY_DIAGNOSIS_DETAIL;
import static com.app.etouchcare.extra.Keys.EndPointPatientList.KEY_EMAIL;
import static com.app.etouchcare.extra.Keys.EndPointPatientList.KEY_EM_NAME;
import static com.app.etouchcare.extra.Keys.EndPointPatientList.KEY_EM_PHONE;
import static com.app.etouchcare.extra.Keys.EndPointPatientList.KEY_ID;
import static com.app.etouchcare.extra.Keys.EndPointPatientList.KEY_NAME;
import static com.app.etouchcare.extra.Keys.EndPointPatientList.KEY_PATIENTS;
import static com.app.etouchcare.extra.Keys.EndPointPatientList.KEY_PHONE;
import static com.app.etouchcare.extra.Keys.EndPointPatientList.KEY_ROOM;

import java.util.HashMap;

import static com.app.etouchcare.extra.Keys.EndPointPatientTest.*;
import static com.app.etouchcare.extra.Keys.EndPointPatientDiagnosis.*;
import static com.app.etouchcare.extra.Keys.EndPointPatientTreatment.*;

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

                Patients patients = new Patients();
                JSONObject currentPatient = arrayPatients.getJSONObject(i);
                //get current patient id
                if (currentPatient.has(KEY_ID)) {
                    String id = currentPatient.getString(KEY_ID);
                    patients.setId(id);
                }
                //get current patient name
                if (currentPatient.has(KEY_NAME)){
                    String name = currentPatient.getString(KEY_NAME);
                    patients.setName(name);
                }
                if (currentPatient.has(KEY_DIAGNOSIS)){
                    String diagnosis = currentPatient.getString(KEY_DIAGNOSIS);
                    patients.setDiagnosis(diagnosis);
                }
                if (currentPatient.has(KEY_DIAGNOSIS_DETAIL)){
                    String diagnosisDetail = currentPatient.getString(KEY_DIAGNOSIS_DETAIL);
                    patients.setDiagnosisDetails(diagnosisDetail);
                }
                if (currentPatient.has(KEY_ROOM)){
                    String room = currentPatient.getString(KEY_ROOM);
                    patients.setRoom(room);
                }
                if (currentPatient.has(KEY_CONDITION)){
                    String condition = currentPatient.getString(KEY_CONDITION);
                    patients.setCondition(condition);
                }
                if (currentPatient.has(KEY_ADDRESS)){
                    patients.setAddress(currentPatient.getString(KEY_ADDRESS));
                }
                if (currentPatient.has(KEY_EMAIL)){
                    patients.setEmail(currentPatient.getString(KEY_EMAIL));
                }
                if (currentPatient.has(KEY_EM_NAME)){
                    patients.setEmergencyName(currentPatient.getString(KEY_EM_NAME));
                }
                if (currentPatient.has(KEY_EM_PHONE)){
                    patients.setEmergencyPhone(currentPatient.getString(KEY_EM_PHONE));
                }
                if (currentPatient.has(KEY_PHONE)){
                    patients.setPhone(currentPatient.getString(KEY_PHONE));
                }
                if (currentPatient.has(KEY_AGE)){
                    patients.setPhone(currentPatient.getString(KEY_AGE));
                }

                listPatients.add(patients);
                Log.d("wenzhong",patients.toString());
            }
//            Log.d("wenzhong",listPatients.toString());

        } catch (JSONException e) {
            e.printStackTrace();

        }
        if (mPatientListLoaded != null) mPatientListLoaded.onPatientListLoaded(listPatients);

    }

    public static void parseTestJSONResponse(JSONObject response, PatientTestLoadedListener patientTestLoadedListener){
//        ArrayList<HashMap<String,String>> listTest = new ArrayList<>();
        ArrayList<Test> listTest = new ArrayList<>();
        if (response==null||response.length()==0){
            return;
        }
        try {
            JSONArray arrayTests = response.getJSONArray(KEY_TEST_ROOT);
            for (int i=0;i<arrayTests.length();i++){
                JSONObject currentPatient = arrayTests.getJSONObject(i);

                Test test = new Test();
                //get current patient id
                if (currentPatient.has(KEY_TEST_ID)) {
                    String id = currentPatient.getString(KEY_TEST_ID);
                    test.set_id(id);
                }
                //get current patient name
                if (currentPatient.has(KEY_TEST_NAME)) {
                    String name = currentPatient.getString(KEY_TEST_NAME);
                    test.setName(name);
                }
                //get current patient diagnosis
                if (currentPatient.has(KEY_TEST_DATE)) {
                    String date = currentPatient.getString(KEY_TEST_DATE);
                    test.setDate(date);
                }
                //get current patient diagnosis detail
                if (currentPatient.has(KEY_TEST_PATIENT_ID)) {
                    String patientID = currentPatient.getString(KEY_TEST_PATIENT_ID);
                    test.setPatientId(patientID);

                }
                //get current patient room
                if (currentPatient.has(KEY_TEST_TESTID)) {
                    String testID = currentPatient.getString(KEY_TEST_TESTID);
                    test.setTestId(testID);
                }

                if (currentPatient.has(KEY_TEST_RESULT)) {
                    String result = currentPatient.getString(KEY_TEST_RESULT);
                    test.setResult(result);
                }

                listTest.add(test);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (patientTestLoadedListener != null) patientTestLoadedListener.onPatientTestLoaded(listTest);
    }

    public static void parseTrialJSONResponse(JSONObject response, TrialsLoadedListener trialsLoadedListener){
        ArrayList<HashMap<String,String>> listTest = new ArrayList<>();
        ArrayList<String> trialsArray = new ArrayList<String>();
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

                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(KEY_TEST_ID,id);
                hashMap.put(KEY_TEST_NAME,name);
                listTest.add(hashMap);
                trialsArray.add(name);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (trialsLoadedListener != null) trialsLoadedListener.onTrialsLoaded(trialsArray, listTest);
    }

    public static void parseDiagnosisJSONResponse(JSONObject response, PatientDiagnosisLoadedListener patientDiagnosisLoadedListener){
        ArrayList<Diagnosis> listTest = new ArrayList<>();
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


                Diagnosis diagnosis = new Diagnosis(id,patientID,description,date);


                listTest.add(diagnosis);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (patientDiagnosisLoadedListener != null){
            patientDiagnosisLoadedListener.onPatientDiagnosisLoaded(listTest);
//            Log.d("Parser","Check parsed list: \n"+listTest.toString());
        }

        else
            Log.d("Parser","Null diagnosisloadedListener");
    }

    public static void parseTreatmentJSONResponse(JSONObject response, PatientTreatmentLoadedListener patientTreatmentLoadedListener) {
        ArrayList<Treatments> listTest = new ArrayList<>();
        if (response == null || response.length() == 0) {
            Log.d("parseTreatment","No found response");
            return;
        }
        try {
            JSONArray arrayTests = response.getJSONArray(KEY_TREAT_ROOT);
            for (int i = 0; i < arrayTests.length(); i++) {
                JSONObject currentPatient = arrayTests.getJSONObject(i);
                //get current patient id
                String id = currentPatient.getString(KEY_TREAT_ID);
                //get current patient name
                String description = currentPatient.getString(KEY_TREAT_DESCRPTION);
                //get current patient TREATnosis
                String date = currentPatient.getString(KEY_TREAT_DATE);
                //get current patient TREATnosis detail
                String patientID = currentPatient.getString(KEY_TREAT_PATIENT_ID);
                Treatments treatments = new Treatments(id,description,date,patientID);
                listTest.add(treatments);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
            patientTreatmentLoadedListener.onPatientTreatmentLoaded(listTest);
//            Log.d("Parser", "Check treat list: \n" + listTest.toString());

    }
}
