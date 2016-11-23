package com.app.etouchcare.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.app.etouchcare.R;
import com.app.etouchcare.adapters.SampleFragmentPagerAdapter;
import com.app.etouchcare.datamodel.Patients;
import com.app.etouchcare.fragments.MainContentFragment;

import java.util.ArrayList;

public class PatientDetailActivity extends AppCompatActivity {

    private String patientID = "";
    private Patients theOne;
    private ArrayList<Patients> patientList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        patientID = getIntent().getStringExtra("id");
        patientList = getIntent().getParcelableArrayListExtra(MainContentFragment.PATIENT_LIST);
        theOne = findTheOne(patientID,patientList);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager(),
                this));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);


    }
    public String getPatientID(){
        return patientID;
    }
    public Patients getTheOne(){
        return theOne;
    }

    private Patients findTheOne(String id, ArrayList<Patients> list){
        for (Patients one:list) {
            if(one.getId().equals(id)) return one;
        }
        return null;
    }
}
