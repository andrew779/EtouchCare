package com.app.etouchcare.callbacks;

import com.app.etouchcare.datamodel.Patients;

import java.util.ArrayList;

/**
 * Created by wenzhongzheng on 2016-11-20.
 */

public interface PatientListLoadedListener {
    void onPatientListLoaded(ArrayList<Patients> patientList);
}
