package com.app.etouchcare.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.etouchcare.R;
import com.app.etouchcare.datamodel.Patients;
import com.google.gson.Gson;

public class AddPatient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);


        Button btnAdd = (Button) this.findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(AddPatient.this, "test", Toast.LENGTH_SHORT).show();
                EditText txtName = (EditText) findViewById(R.id.editTextName);
                EditText txtRoom = (EditText) findViewById(R.id.editTextRoom);
                EditText txtEmail = (EditText) findViewById(R.id.editTextEmail);
                EditText txtPhone = (EditText) findViewById(R.id.editTextPhone);
                EditText txtAddress = (EditText) findViewById(R.id.editTextAddress);
                EditText txtEmgrName = (EditText) findViewById(R.id.editTextEmergencyName);
                EditText txtEmgrPhone = (EditText) findViewById(R.id.editTextEmergencyPhone);


            }
        });
    }


}
