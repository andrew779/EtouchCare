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

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        //Get Views
        TextView txtName = (TextView) findViewById(R.id.tv_nameBind);
        TextView txtRoom = (TextView) findViewById(R.id.tv_roomBind);
        TextView txtId = (TextView) findViewById(R.id.tv_idBind);

        //Intent Patient ID:
        Intent intent_id = getIntent();
        Integer intentExtra = (Integer)intent_id.getExtras().get("PatientPosition");

        //Toast.makeText(this, intentExtra, Toast.LENGTH_SHORT).show();
        PatientModel modelSource = new PatientModel();
        PatientModel model = modelSource.GetTop3Patients().get(intentExtra);

        //txtName.setTextSize(40);
        txtName.setText(model.Name);
        txtId.setText(model.Id);
        txtRoom.setText(model.Room);
    }
}
