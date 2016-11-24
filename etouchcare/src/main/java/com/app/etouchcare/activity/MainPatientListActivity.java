package com.app.etouchcare.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

<<<<<<< HEAD
=======
import com.app.etouchcare.datamodel.Patients;
>>>>>>> f7cf8675e1a094a013de591979dfd9bdb71a1454
import com.app.etouchcare.fragments.MainContentFragment;
import com.app.etouchcare.R;
import com.app.etouchcare.fragments.UserProfileFragment;

<<<<<<< HEAD
=======
import java.util.ArrayList;

>>>>>>> f7cf8675e1a094a013de591979dfd9bdb71a1454
public class MainPatientListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainContentFragment.OnFetchIDListener {

    private String patientID="";
<<<<<<< HEAD

=======
>>>>>>> f7cf8675e1a094a013de591979dfd9bdb71a1454
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_patient_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

<<<<<<< HEAD

=======
>>>>>>> f7cf8675e1a094a013de591979dfd9bdb71a1454
        if (savedInstanceState == null){
            MainContentFragment mainContent = new MainContentFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, mainContent, mainContent.getTag()).commit();
        }

<<<<<<< HEAD


=======
>>>>>>> f7cf8675e1a094a013de591979dfd9bdb71a1454
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

    @SuppressWarnings("StatementWithEmptyBody")
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
//        Toast.makeText(this,"Parent get id: "+id,Toast.LENGTH_SHORT).show();
    }
}
