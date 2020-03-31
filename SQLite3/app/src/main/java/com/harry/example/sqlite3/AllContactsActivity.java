package com.harry.example.sqlite3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.harry.example.sqlite3.contacts.Contacts;
import com.harry.example.sqlite3.myadapter.MyAdapter;
import com.harry.example.sqlite3.update.UpdateData;

import java.util.List;

public class AllContactsActivity extends AppCompatActivity{

    private Toolbar toolbar;
    private List<Contacts> contactsList;
    private ListView listView;
    private TextView contact_ID;
    private TextView contact_USER;
    private TextView contact_NUMBER;
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_contacts);
        context = getApplicationContext();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("CONTACTS LIST");
        Contacts contacts = new Contacts();
        contactsList = contacts.getContactList();
        listView = findViewById(R.id.list_view);
        myAdapter = new MyAdapter(this,context, contactsList);
        listView.setAdapter(myAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("TAG", "onRestart: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("TAG", "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("TAG", "onResume: ");
    }
}




