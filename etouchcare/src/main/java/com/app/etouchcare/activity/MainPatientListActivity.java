/**
 * TeamOne
 */
package com.app.etouchcare.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.app.etouchcare.datamodel.Patients;
import com.app.etouchcare.fragments.MainContentFragment;
import com.app.etouchcare.R;
import com.app.etouchcare.fragments.PatientTestsFragment;
import com.app.etouchcare.fragments.UserProfileFragment;

import java.util.ArrayList;

import static com.app.etouchcare.extra.mUrls.getAllPatients.URL_PATIENT_TEST;

public class MainPatientListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainContentFragment.OnFetchIDListener {

    private String patientID="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_patient_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null){
            MainContentFragment mainContent = new MainContentFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, mainContent, mainContent.getTag()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

//    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_patientlist) {
            MainContentFragment mainContent = new MainContentFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, mainContent, mainContent.getTag()).commit();

        } else if (id == R.id.nav_profile) {
            UserProfileFragment userProfileFragment = new UserProfileFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, userProfileFragment, userProfileFragment.getTag()).commit();

        } else if (id == R.id.nav_logout) {
            NavUtils.navigateUpFromSameTask(this);
        } else if (id == R.id.nav_aboutme){

            aboutMeAlert();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public String getPatientID(){
        return patientID;
    }
    @Override
    public void onFetchID(String id) {
        patientID = id;
    }

    private void aboutMeAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Add the buttons
        builder.setTitle("About Us");
        builder.setMessage("Team One\n" +
                            "Team Members:\n" +
                "Guilherme Morais\n" +
                "Jose Apablaza\n" +
                "Wenzhong Zheng");

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        // Set other dialog properties

        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.setCancelable(true);
        dialog.show();
    }

}
