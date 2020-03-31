
package com.harry.example.sqlite3.update;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.harry.example.sqlite3.MainActivity;
import com.harry.example.sqlite3.R;
import com.harry.example.sqlite3.contacts.Contacts;

public class UpdateData extends AppCompatActivity implements View.OnClickListener {

    private TextView contact_id;
    private EditText contact_user;
    private EditText contact_number;
    private Toolbar toolbar;
    private static Context context;
    private Button update;
    private MainActivity mainActivity;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_layout);
        context = getApplicationContext();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mainActivity = new MainActivity();
        contact_id = findViewById(R.id.contact_id);
        contact_user = findViewById(R.id.contact_user);
        contact_number = findViewById(R.id.contact_number);
        intent = getIntent();
        Contacts contacts = (Contacts) intent.getSerializableExtra("contacts");
        contact_id.setText(String.valueOf(contacts.getId()));
        contact_user.setText(contacts.getName());
        contact_number.setText(contacts.getPhone_number());
        update = findViewById(R.id.update);
        update.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        int user_id = Integer.parseInt(contact_id.getText().toString());
        String username = contact_user.getText().toString();
        String phone_number = contact_number.getText().toString();
        Contacts contacts = new Contacts(user_id, username, phone_number);
        mainActivity.update(contacts);
        setResult(2,intent);
        finish();
    }
}
