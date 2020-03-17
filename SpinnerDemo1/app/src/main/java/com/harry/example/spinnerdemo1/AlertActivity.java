package com.harry.example.spinnerdemo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AlertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_activity);
        Intent intent=getIntent();
        String country_name=intent.getStringExtra("country_name");
        TextView textView =findViewById(R.id.alertTextView);
        textView.setText(country_name);
        Toast.makeText(getApplicationContext(),country_name,Toast.LENGTH_LONG).show();
    }

    public void closeDialog(View view) {
        finish();
    }
}
