package com.app.etouchcare.extra;

/**
 * Created by wenzhongzheng on 2016-11-19.
 */

public interface Keys {
    interface EndPointPatientList{
        String KEY_PATIENTS = "patients";
        String KEY_ID = "_id";
        String KEY_ADDRESS = "address";
        String KEY_CONDITION = "condition";
        String KEY_DIAGNOSIS = "diagnosis";
        String KEY_DIAGNOSIS_DETAIL = "diagnosisDetails";
        String KEY_EMAIL = "email";
        String KEY_EM_NAME = "emergencyName";
        String KEY_EM_PHONE = "emergencyPhone";
        String KEY_PHONE = "phone";
        String KEY_NAME = "pname";
        String KEY_ROOM = "room";


    }
    interface EndPointPatientTest{
        String KEY_TEST_ROOT = "tests";
        String KEY_TEST_ID = "_id";
        String KEY_TEST_PATIENT_ID = "patientId";
        String KEY_TEST_TESTID = "testId";
        String KEY_TEST_NAME = "name";
        String KEY_TEST_DATE = "date";
        String KEY_TEST_RESULT = "results";
    }

    interface EndPointPatientDiagnosis{
        String KEY_DIAG_ROOT = "tests";
        String KEY_DIAG_ID = "_id";
        String KEY_DIAG_PATIENT_ID = "patientId";
        String KEY_DIAG_DESCRPTION = "description";
        String KEY_DIAG_DATE = "date";

    }
    interface EndPointPatientTreatment{
        String KEY_TREAT_ROOT = "tests";
        String KEY_TREAT_ID = "_id";
        String KEY_TREAT_PATIENT_ID = "patientId";
        String KEY_TREAT_DESCRPTION = "description";
        String KEY_TREAT_DATE = "date";
    }

}
