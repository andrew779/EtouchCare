package com.app.etouchcare.callbacks;

import com.app.etouchcare.datamodel.Patients;

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
        void onPatientDiagnosisLoaded(ArrayList<HashMap<String,String>> diagnosisList);
    }
    interface PatientTestLoadedListener {
        void onPatientTestLoaded(ArrayList<HashMap<String,String>> testList);
    }
}
