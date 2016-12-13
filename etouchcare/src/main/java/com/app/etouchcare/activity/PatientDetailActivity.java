/**
 * TeamOne
 */
package com.app.etouchcare.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_patient_list, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
