package com.harry.example.sqlite3;

//mydatabasehandler,writable_database and writable_database shows null while calling update and delete
// how to start Update and Delete activity from AllContactsActivity using listview layout view
//and how to change the Adapter view data after updation and coming back to activity
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.harry.example.sqlite3.contacts.Contacts;
import com.harry.example.sqlite3.mydatabase.MyDatabaseHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText user_name;
    private EditText phone_number;
    private MyDatabaseHandler myDatabaseHandler;
    private SQLiteDatabase writable_sqlitedatabase;
    private SQLiteDatabase readable_sqlitedatabse;
    private Button save;
    private Button retrive;
    private Toolbar toolbar;
    private  static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.sub_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        context=getApplicationContext();
        user_name = findViewById(R.id.username);
        phone_number = findViewById(R.id.phone_number);
        myDatabaseHandler = new MyDatabaseHandler(this);
        writable_sqlitedatabase = myDatabaseHandler.getWritableDatabase();
        readable_sqlitedatabse = myDatabaseHandler.getReadableDatabase();
        save=findViewById(R.id.save);
        retrive=findViewById(R.id.retrieve);
        save.setOnClickListener(this);
        retrive.setOnClickListener(this);
//        Contacts contacts=new Contacts();
//        contacts.setName("Bikash");
//        contacts.setPhone_number("8093883243");
//        long rows=myDatabaseHandler.addContacts(writable_sqlitedatabase,contacts);
//        if(rows > 0){
//            Toast.makeText(getApplicationContext(),"DATA INSERTED",Toast.LENGTH_LONG).show();
//        }else{
//            Toast.makeText(getApplicationContext(),"DATA NOT INSERTED",Toast.LENGTH_LONG).show();
//        }
//        List<Contacts> contactsList=myDatabaseHandler.getAllContacts(readable_sqlitedatabse);
//        if (!contactsList.isEmpty()){
//            StringBuilder stringBuilder=new StringBuilder();
//            for(int i=0;i<contactsList.size();i++){
//                Contacts con=contactsList.get(i);
//                stringBuilder.append("ID = ").append(con.getId()).append("USERNAME = ").append(con.getName()).append("\n").append(" PHONE NUMBER = ").append(con.getPhone_number()).append("\n");
//            }
//            Toast.makeText(getApplicationContext(),stringBuilder.toString(),Toast.LENGTH_LONG).show();
//        }
//        writable_sqlitedatabase.close();
//        readable_sqlitedatabse.close();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String username = user_name.getText().toString();
        String phonenumber = phone_number.getText().toString();
        switch (id) {
            case R.id.save:
                Toast.makeText(getApplicationContext(), "Hy", Toast.LENGTH_LONG).show();
                insert(username, phonenumber);
                break;
            // case R.id.update:
            //update(username, phonenumber);
            //    break;
            case R.id.retrieve:
                getAll();
                break;
            // case R.id.delete:
            //delete(username, phonenumber);
            // break;
            //case R.id.truncate:
            //deleteAll(username, phonenumber);
            // break;
            default:
                break;
        }
    }


    private void getAll() {
        List<Contacts> contactsList = myDatabaseHandler.getAllContacts(writable_sqlitedatabase);
        //Toast.makeText(getApplicationContext(),String.valueOf(contactsList.size()),Toast.LENGTH_LONG).show();
        if(contactsList.size() == 0){
            Toast.makeText(context,"NO DATA",Toast.LENGTH_LONG).show();
            return;
        }
        contactsList.get(0).setContactList(contactsList);
        Intent intent = new Intent(getApplicationContext(), AllContactsActivity.class);
        startActivity(intent);
    }

    private boolean validateData(String username, String phonenumber) {
        return !username.equals(" ") && !username.isEmpty() && !phonenumber.equals(" ") && !phonenumber.isEmpty() && username != null && phonenumber != null;
    }
//    private void update(String username, String phonenumber){
//        Contacts contacts=new Contacts(username,phonenumber);
//        c
//    }

    private void insert(String username, String phonenumber) {
        if (validateData(username, phonenumber)) {
            Contacts contacts = new Contacts(username, phonenumber);
            long rows = myDatabaseHandler.addContacts(writable_sqlitedatabase, contacts);
            if (rows > -1) {
                Toast.makeText(getApplicationContext(), "DATA INSERTED SUCCESSFULLY", Toast.LENGTH_LONG).show();
                user_name.setText("");
                phone_number.setText("");
            } else {
                Toast.makeText(getApplicationContext(), "DATA NOT INSERTED", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "INVALID DATA", Toast.LENGTH_LONG).show();
        }
    }
    public void update(Contacts contacts){
       MyDatabaseHandler myDatabaseHandler=new MyDatabaseHandler(context);
       SQLiteDatabase sqLiteDatabase=myDatabaseHandler.getWritableDatabase();
        if(contacts != null){
            int rows=myDatabaseHandler.updateData(sqLiteDatabase,contacts);
            if(rows > 0){
                Toast.makeText(MainActivity.context,"UPDATED SUCCESSFULLY",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(MainActivity.context,"NOT UPDATED SUCCESSFULLY",Toast.LENGTH_LONG).show();
            }
        }
   }
   public void delete(Contacts contacts){
        MyDatabaseHandler myDatabaseHandler=new MyDatabaseHandler(context);
        SQLiteDatabase sqLiteDatabase=myDatabaseHandler.getWritableDatabase();
        int rows=myDatabaseHandler.delete(sqLiteDatabase,contacts);
        if(rows>0){
            Toast.makeText(MainActivity.context,"DELETED",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.context,"NOT DELETED",Toast.LENGTH_LONG).show();
        }
   }


}
