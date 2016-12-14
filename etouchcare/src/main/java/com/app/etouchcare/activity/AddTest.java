package com.app.etouchcare.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.app.etouchcare.R;
import com.app.etouchcare.callbacks.PatientLoadedListener;
import com.app.etouchcare.extra.PatientUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class AddTest extends AppCompatActivity implements PatientLoadedListener.TrialsLoadedListener {
    private PatientUtils utils;
    private ArrayList<HashMap<String,String>> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        utils = new PatientUtils();
        utils.loadTrials(this);
    }

    public void setTrialsList(ArrayList<HashMap<String,String>> trialsList){
        data = trialsList;
    }


    @Override
    public void onTrialsLoaded(ArrayList<HashMap<String, String>> trialsList) {
        data = trialsList;
        //adapter.notifyItemRangeChanged(0,data.size());

        String strArr[] = new String[2];
        int i = 0;
        for (HashMap<String, String> hash : data) {
            for (String current : hash.values()) {
                strArr[i] = current;
                i++;
            }
        }
        Spinner spinner = (Spinner) findViewById(R.id.spinnerTrials);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                 android.R.layout.simple_spinner_item, strArr);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }
}
