package com.app.etouchcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.app.etouchcare.MyPatientsListActivity;
import com.app.etouchcare.R;


public class LoginActivity extends AppCompatActivity {

    public final static String USER_ID = "com.app.etouchcare.ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    //Log In Button:
    public void logIn(View view) {
        Intent intent_id = new Intent(this, MyPatientsListActivity.class);
        EditText et_uid = (EditText) findViewById(R.id.et_uid);
        String u_id = et_uid.getText().toString();
        intent_id.putExtra(USER_ID, u_id);
        startActivity(intent_id);

    }


}
