package com.app.etouchcare.json;

import android.util.Log;

import com.app.etouchcare.datamodel.Patients;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.app.etouchcare.extra.Keys.EndPointPatientList.KEY_CONDITION;
import static com.app.etouchcare.extra.Keys.EndPointPatientList.KEY_DIAGNOSIS;
import static com.app.etouchcare.extra.Keys.EndPointPatientList.KEY_DIAGNOSIS_DETAIL;
import static com.app.etouchcare.extra.Keys.EndPointPatientList.KEY_ID;
import static com.app.etouchcare.extra.Keys.EndPointPatientList.KEY_NAME;
import static com.app.etouchcare.extra.Keys.EndPointPatientList.KEY_PATIENTS;
import static com.app.etouchcare.extra.Keys.EndPointPatientList.KEY_ROOM;

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
            Log.d("wenzhong",listPatients.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listPatients;
    }
}
