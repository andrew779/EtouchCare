package com.app.etouchcare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.app.etouchcare.R;

public class ConditionActivity extends AppCompatActivity {

    //temp fake data
    String[] title = {
            "Cough",
            "Fatigue",
            "Fever, chills",
            "Sore throat",
            "Headache"
    };
    String[] description = {
            "Cough Description",
            "Fatigue Description",
            "Fever, chills Description",
            "Sore throat Description",
            "Headache Description"
    };
    String[] url = {
            "",
            "",
            "",
            "",
            ""
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condition);

        ConditionAdapter adapter = new ConditionAdapter(this,title,description,url);
        ListView listView = (ListView) findViewById(R.id.condition_listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(this," Clicked",Toast.LENGTH_SHORT).show();
                Toast.makeText(ConditionActivity.this,position+" Clicked",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
