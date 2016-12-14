package com.app.etouchcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.app.etouchcare.datamodel.Patients;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.app.etouchcare.R;
import com.app.etouchcare.callbacks.PatientLoadedListener;
import com.app.etouchcare.datamodel.Patients;
import com.app.etouchcare.datamodel.Test;
import com.app.etouchcare.extra.PatientUtils;
import com.app.etouchcare.network.VolleySingleton;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class AddTest extends AppCompatActivity implements PatientLoadedListener.TrialsLoadedListener {
    private PatientUtils utils;
    private Patients theOne;
    private ArrayList<String> data = new ArrayList<String>();
    private ArrayList<HashMap<String,String>> trialsData;
    final String URL = "https://mapd2016.herokuapp.com/patienttests/";
    String json="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent i = getIntent();
        theOne = i.getParcelableExtra("Patient");

        utils = new PatientUtils();
        utils.loadTrials(this);

        final AddTest addTest = this;
        Button btnAdd = (Button) this.findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddTest.this, "Adding", Toast.LENGTH_SHORT).show();
                EditText txtResults = (EditText) findViewById(R.id.editTextResults);
                EditText txtData = (EditText) findViewById(R.id.editTextDate);
                Spinner spnTrial = (Spinner) findViewById(R.id.spinnerTrials);

                Test test = new Test();
                test.setName(spnTrial.getSelectedItem().toString());
                test.setPatientId(theOne.getId());
                test.setDate(txtData.getText().toString());
                test.setResult(txtResults.getText().toString());

                //addTest(patient);
                Gson gson = new Gson();
                json = gson.toJson(test);

                utils.addTest(AddTest.this,json);

                finish();
            }
        });
    }

    @Override
    public void onTrialsLoaded(ArrayList<String> trialsList, ArrayList<HashMap<String,String>> trialsHashList) {
        data = trialsList;
        trialsData = trialsHashList;

        Spinner spinner = (Spinner) findViewById(R.id.spinnerTrials);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                 android.R.layout.simple_spinner_item, trialsList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
        }

        return super.onOptionsItemSelected(item);
    }
}