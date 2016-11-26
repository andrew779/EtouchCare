package com.app.etouchcare.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.etouchcare.R;
import com.app.etouchcare.datamodel.Treatments;
import com.app.etouchcare.extra.PatientUtils;
import com.google.gson.Gson;

public class AddTreatmentActivity extends AppCompatActivity {

    private String patientId = "";
    private EditText description,date;
    private PatientUtils patientUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_treatment);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        patientUtils = new PatientUtils();
        patientId = getIntent().getStringExtra("id");

        description = (EditText) findViewById(R.id.add_treat_des);
        date = (EditText) findViewById(R.id.treat_date);
        Button submit = (Button) findViewById(R.id.add_treat_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Treatments treatments = new Treatments();
                treatments.setDate(date.getText().toString());
                treatments.setDescription(date.getText().toString());
                treatments.setPatientId(patientId);
                Gson gson = new Gson();
                String body = gson.toJson(treatments);
                patientUtils.addTreatment(getApplication(),body);


            }
        });

    }
}
