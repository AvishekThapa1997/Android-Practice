package com.harry.example.sqlite3.myadapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.harry.example.sqlite3.AllContactsActivity;
import com.harry.example.sqlite3.R;
import com.harry.example.sqlite3.contacts.Contacts;
import com.harry.example.sqlite3.delete.DeleteData;
import com.harry.example.sqlite3.update.UpdateData;

import java.util.List;

public class MyAdapter extends BaseAdapter implements View.OnClickListener {

    private Context context;
    private List<Contacts> contactsList;
    private MyHolder myHolder;
    private AllContactsActivity allContactsActivity;
    private Activity activity;
    public MyAdapter(Activity activity,Context context, List<Contacts> contactsList) {
        this.context = context;
        this.contactsList = contactsList;
        this.activity=activity;
    }

    @Override
    public int getCount() {
        return contactsList.size();
    }

    @Override
    public Contacts getItem(int position) {
        return contactsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.contact_listview, parent, false);
            myHolder = new MyHolder(view);
            //allContactsActivity=new AllContactsActivity();
        }
        Contacts contacts = contactsList.get(position);
        myHolder.contact_ID.setText(String.valueOf(contacts.getId()));
        myHolder.contact_USER.setText(contacts.getName());
        myHolder.contact_NUMBER.setText(contacts.getPhone_number());
        myHolder.update = view.findViewById(R.id.update);
        myHolder.delete = view.findViewById(R.id.delete);
        myHolder.update.setTag(contacts);
        myHolder.delete.setTag(contacts);
        myHolder.update.setOnClickListener(this);
        myHolder.delete.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View v) {
        Contacts contacts = (Contacts) v.getTag();
        Intent intent=null;
        if(v .getId() == R.id.update) {
            intent = new Intent(context, UpdateData.class);
        }else{
            intent=new Intent(context, DeleteData.class);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("contacts", contacts);
        context.startActivity(intent);
        //startActivityForResult(intent, 1);
        //activity.startActivityForResult(intent,1);
        //Log.i("TAG", "onClick: ");
    }
    private static class MyHolder {
        private TextView contact_ID;
        private TextView contact_USER;
        private TextView contact_NUMBER;
        private Button update;
        private Button delete;
        private MyHolder(View view) {
            contact_ID = view.findViewById(R.id.contact_id);
            contact_USER = view.findViewById(R.id.contact_user);
            contact_NUMBER = view.findViewById(R.id.contact_number);
        }
    }
}
