package com.app.etouchcare.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.app.etouchcare.datamodel.Patients;
import com.android.volley.toolbox.JsonObjectRequest;
import com.app.etouchcare.R;
import com.app.etouchcare.callbacks.PatientLoadedListener;
import com.app.etouchcare.datamodel.Patients;
import com.app.etouchcare.datamodel.Test;
import com.app.etouchcare.datamodel.Treatments;
import com.app.etouchcare.extra.PatientUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.Calendar;


public class AddTreatmentActivity extends AppCompatActivity  {
    private PatientUtils utils;
    private Patients theOne;
    final String URL = "https://mapd2016.herokuapp.com/patienttreatments/";
    String json="";
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_treatment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //calender stuff
        dateView = (TextView) findViewById(R.id.textViewDate);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);

        Intent i = getIntent();
        theOne = i.getParcelableExtra("Patient");
        utils = new PatientUtils();

        //final AddTreatmentActivity addTest = this;
        Button btnAdd = (Button) this.findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddTreatmentActivity.this, "Adding", Toast.LENGTH_SHORT).show();
                EditText txtResults = (EditText) findViewById(R.id.editTextResults);
                EditText txtName = (EditText) findViewById(R.id.editTextName);
                TextView txtData = (TextView) findViewById(R.id.textViewDate);

                Treatments test = new Treatments();
                test.setDescription(txtName.getText().toString());
                test.setPatientId(theOne.getId());
                test.setDate(txtData.getText().toString());

                //addTest(patient);
                Gson gson = new Gson();
                json = gson.toJson(test);

                utils.addTreatment(AddTreatmentActivity.this,json);

                finish();
            }
        });
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
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
