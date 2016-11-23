package com.app.etouchcare.callbacks;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by wenzhongzheng on 2016-11-22.
 */

public interface PatientTestLoadedListener {
    void onPatientTestLoaded(ArrayList<HashMap<String,String>> testList);
}
