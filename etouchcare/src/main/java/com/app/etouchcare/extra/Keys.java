package com.app.etouchcare.extra;

/**
 * Created by wenzhongzheng on 2016-11-19.
 */

public interface Keys {
    interface EndPointPatientList{
        String KEY_PATIENTS = "patients";
        String KEY_NAME = "name";
        String KEY_ID = "_id";
        String KEY_DIAGNOSIS = "diagnosis";
        String KEY_DIAGNOSIS_DETAIL = "diagnosisDetails";
        String KEY_ROOM = "room";
        String KEY_CONDITION = "condition";
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

}
