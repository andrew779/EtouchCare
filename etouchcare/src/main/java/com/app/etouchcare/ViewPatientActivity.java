package com.app.etouchcare;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.etouchcare.R;

public class ViewPatientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patient);

<<<<<<< HEAD
=======
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        //Get ListView
        TextView txtName = (TextView) findViewById(R.id.tv_name);

        //Setup List Data
        //prepareListData();


        //Intent User ID:
        Intent intent_id = getIntent();
        String intentExtra = intent_id.getExtras().get("PatientPosition").toString();

        Toast.makeText(this, intentExtra, Toast.LENGTH_SHORT).show();

        txtName.setTextSize(40);
        txtName.setText(intentExtra);

>>>>>>> 519030fd9054e1d28a86c1abf9ebcf0a77178537
    }
}
