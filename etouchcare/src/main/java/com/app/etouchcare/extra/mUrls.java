package com.app.etouchcare.extra;

/**
 * Created by wenzhongzheng on 2016-11-20.
 */

public interface mUrls {
    interface getAllPatients{

        String URL_LIST_ALL_PATIENTS = "https://mapd2016.herokuapp.com/";
        String URL_PATIENT_TEST = "https://mapd2016.herokuapp.com/patienttests/";
        String URL_PATIENT_TREAT = "https://mapd2016.herokuapp.com/patienttreatments/";
        String URL_PATIENT_DIAG = "https://mapd2016.herokuapp.com/patientconditions/";
        String URL_TRIALS = "https://mapd2016.herokuapp.com/trials/all";
    }
}
