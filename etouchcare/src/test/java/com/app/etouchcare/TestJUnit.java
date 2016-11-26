package com.app.etouchcare;

/**
 * Created by wenzhongzheng on 2016-11-25.
 */
import com.app.etouchcare.activity.AddPatient;
import com.app.etouchcare.callbacks.PatientLoadedListener;
import com.app.etouchcare.datamodel.Patients;
import com.app.etouchcare.extra.PatientUtils;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestJUnit implements PatientLoadedListener.PatientListLoadedListener{


    @Test

    public void testPushPatientList() {

        Patients patients = new Patients("16","Whong","Flu","114","not too bad","Cough");

        AddPatient addPatient = new AddPatient();
        addPatient.addPatient(patients);
        assertNotNull(patients);

    }

    @Override
    public void onPatientListLoaded(ArrayList<Patients> patientList) {
        assertNotNull(patientList);
    }

    @Test
    public void loadPatientList(){
        PatientUtils patientUtils = new PatientUtils();
        patientUtils.loadPatientList(this);
    }





}
