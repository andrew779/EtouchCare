package com.app.etouchcare.callbacks;

import com.app.etouchcare.datamodel.Diagnosis;
import com.app.etouchcare.datamodel.Patients;
import com.app.etouchcare.datamodel.Test;
import com.app.etouchcare.datamodel.Treatments;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by wenzhongzheng on 2016-11-20.
 */

public interface PatientLoadedListener {
    interface PatientListLoadedListener{
        void onPatientListLoaded(ArrayList<Patients> patientList);
    }

    interface PatientDiagnosisLoadedListener{
        void onPatientDiagnosisLoaded(ArrayList<Diagnosis> diagnosisList);
    }
    interface PatientTestLoadedListener {
        void onPatientTestLoaded(ArrayList<Test> testList);
    }
    interface TrialsLoadedListener {
        void onTrialsLoaded(ArrayList<String> trialsList, ArrayList<HashMap<String,String>> trialsHashList);
    }
    interface PatientTreatmentLoadedListener {
        void onPatientTreatmentLoaded(ArrayList<Treatments> treatmentList);
    }
    interface RecordDeletedListener{
        void onRecordDeleted(int position);
    }
}
