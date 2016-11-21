//*
// * TeamOne
//
//
//package com.app.etouchcare.patientlist;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.ArrayList;
//
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.widget.TabHost;
//import android.widget.TextView;
//
//
//import android.widget.ExpandableListView;
//
//import com.app.etouchcare.R;
//import com.app.etouchcare.activity.LoginActivity;
//
//
//public class MyPatientsListActivity extends AppCompatActivity {
//    ExpandableListAdapter listAdapter;
//    ExpandableListView expListView;
//    List<String> listDataHeader;
//    HashMap<String, List<String>> listDataChild;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_patients_list);
//
//        //Get ListView
//        expListView = (ExpandableListView) findViewById(R.id.elv_patients);
//
//        //Setup List Data
//        prepareListData();
//
//        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
//
//        //Setting list Adapter
//        expListView.setAdapter(listAdapter);
//
//        //Intent User ID:
//        Intent intent_id = getIntent();
//        String u_id = intent_id.getStringExtra(LoginActivity.USER_ID);
//        TextView tv_uname = new TextView(this);
//        tv_uname.setTextSize(40);
//        tv_uname.setText(u_id);
//
//        //Declaration:
//        TabHost host = (TabHost)findViewById(R.id.th_list);
//        host.setup();
//
//        //Tab1:
//        TabHost.TabSpec spec = host.newTabSpec("My Patients");
//        spec.setContent(R.id.My_Patients);
//        spec.setIndicator("My Patients");
//        host.addTab(spec);
//
//        //Tab2:
//        spec = host.newTabSpec("My Clinical Trial");
//        spec.setContent(R.id.Clinical_Trials);
//        spec.setIndicator("My Clinical Trial");
//        host.addTab(spec);
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.my_patients_list, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//
//    //Setup List Data:
//    private void prepareListData() {
//
//        listDataHeader = new ArrayList<String>();
//        listDataChild = new HashMap<String, List<String>>();
//
//        //Add Header data: Level 1
//        listDataHeader.add("Manuel Rodriguez, ID: 574743");
//        listDataHeader.add("Andrea Smith, ID: 574385");
//        listDataHeader.add("Michael Phelps, ID: 5747434");
//
//        //Add Child Data: Level 2
//        List<String> ManuelRodriguez = new ArrayList<String>();
//        ManuelRodriguez.add("Room: #34");
//        ManuelRodriguez.add("Diagnosis: Flu");
//
//        List<String> AndreaSmith = new ArrayList<String>();
//        AndreaSmith.add("Room: #43");
//        AndreaSmith.add("Diagnosis: Broken Leg");
//
//        List<String> MichaelPhelps = new ArrayList<String>();
//        MichaelPhelps.add("Room: #44");
//        MichaelPhelps.add("Diagnosis: Migraines");
//
//        //(Heather, Child Data)
//        listDataChild.put(listDataHeader.get(0), ManuelRodriguez);
//        listDataChild.put(listDataHeader.get(1), AndreaSmith);
//        listDataChild.put(listDataHeader.get(2), MichaelPhelps);
//    }
//}
