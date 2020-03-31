package com.harry.example.sqlite3.mydatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.harry.example.sqlite3.contacts.Contacts;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHandler extends SQLiteOpenHelper {
    private Context context;

    public MyDatabaseHandler(@Nullable Context context) {
        super(context, Params.DATABASE_NAME, null, Params.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(Params.CREATE_TABLE);
            Toast.makeText(context, "CREATED", Toast.LENGTH_LONG).show();
        } catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(Params.DROP_TABLE);
            onCreate(db);
            Toast.makeText(context, "UPGRADED", Toast.LENGTH_LONG).show();
        } catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
    }

    public long addContacts(SQLiteDatabase sqLiteDatabase, Contacts contacts) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Params.COLUMN_USERNAME, contacts.getName());
        contentValues.put(Params.COLUMN_NUMBER, contacts.getPhone_number());
        try {
            return sqLiteDatabase.insert(Params.TABLE_NAME, null, contentValues);
        } catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
        return -1;
    }

    public List<Contacts> getAllContacts(SQLiteDatabase sqLiteDatabase) {
        List<Contacts> contactsList = new ArrayList<>();
        StringBuffer stringBuffer = new StringBuffer();
        try {
            Cursor cursor = sqLiteDatabase.query(Params.TABLE_NAME, Params.COLUMN_NAMES, null, null, null, null, null, null);
            while (cursor.moveToNext()) {
                int id_index = cursor.getColumnIndex(Params.COLUMN_ID);
                int username_index = cursor.getColumnIndex(Params.COLUMN_USERNAME);
                int number_index = cursor.getColumnIndex(Params.COLUMN_NUMBER);
                int id = cursor.getInt(id_index);
                String username = cursor.getString(username_index);
                String phone_number = cursor.getString(number_index);
                stringBuffer.append("ID = ").append(id).append(" USERNAME = ").append(username).append(" PHONENUMBER ").append(phone_number);
                Contacts contacts = new Contacts(id, username, phone_number);
                contactsList.add(contacts);
            }
            cursor.close();
        } catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
        return contactsList;
    }

    public int updateData(SQLiteDatabase writable_sqlitedatabase, Contacts contacts) {
        ContentValues contentValues=new ContentValues();
        contentValues.put(Params.COLUMN_USERNAME,contacts.getName());
        contentValues.put(Params.COLUMN_NUMBER,contacts.getPhone_number());
        String where=Params.COLUMN_ID+"=?";
        String[] whereClause=new String[]{String.valueOf(contacts.getId())};
        try{
            return writable_sqlitedatabase.update(Params.TABLE_NAME,contentValues,where,whereClause);
        }catch (SQLException sqlexception){
            sqlexception.printStackTrace();
        }
        return 0;
    }
    public int delete(SQLiteDatabase sqLiteDatabase,Contacts contacts){
            String where=Params.COLUMN_ID+"=?";
            String whereClause[] =new String[]{String.valueOf(contacts.getId())};
            try{
                return sqLiteDatabase.delete(Params.TABLE_NAME,where,whereClause);
            }catch (SQLException sqlexception){
                sqlexception.printStackTrace();
            }
            return  0;
    }

    //
    private static class Params {
        private static final String DATABASE_NAME = "contacts";
        private static final int DATABASE_VERSION = 4;

        private static final String TABLE_NAME = "contacts_table";

        //TABLE COLUMN
        private static final String COLUMN_ID = "_id";
        private static final String COLUMN_USERNAME = "username";
        private static final String COLUMN_NUMBER = "phone_number";
        private static final String[] COLUMN_NAMES = {COLUMN_ID, COLUMN_USERNAME, COLUMN_NUMBER};

        //CREATE TABLE
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " ( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_USERNAME + " VARCHAR(255)," +
                COLUMN_NUMBER + " VARCHAR(255)" +
                ");";
        //DROP TABLE
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
