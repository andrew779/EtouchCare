package com.app.etouchcare.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.app.etouchcare.R;
import com.app.etouchcare.datamodel.Patients;
import com.app.etouchcare.network.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

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

                Patients patient = new Patients();
                patient.setpName(txtName.getText().toString());
                patient.setRoom(txtRoom.getText().toString());
                patient.setAddress(txtAddress.getText().toString());
                patient.setEmail(txtEmail.getText().toString());
                patient.setPhone(txtPhone.getText().toString());
                patient.setEmergencyName(txtEmgrName.getText().toString());
                patient.setEmergencyPhone(txtEmgrPhone.getText().toString());

                Gson gson = new Gson();
                String json = gson.toJson(patient);

                final String URL = "http://etouch.azurewebsites.net/";

                JsonObjectRequest req = null;
                try {
                    req = new JsonObjectRequest(URL, new JSONObject(json),
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        VolleyLog.v("Response:%n %s", response.toString(4));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            VolleyLog.e("Error: ", error.getMessage());
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

// add the request object to the queue to be executed
                RequestQueue reqq = VolleySingleton.getInstance().getmRequestQueue();
                reqq.add(req);
            }
        });
    }


}
