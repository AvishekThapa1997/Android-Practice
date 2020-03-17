package com.harry.example.checkboxdemo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private HashMap<Integer,String> hashMap;
    private Button getData;
    private TextView textView;
//    private CheckBox java;
//    private CheckBox python;
//    private CheckBox ruby;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hashMap=new HashMap<>();
        getData=findViewById(R.id.button);
        textView=findViewById(R.id.view_language);
//        java=findViewById(R.id.java);
//        python=findViewById(R.id.python);
//        ruby=findViewById(R.id.ruby);
//        java.setOnCheckedChangeListener(this);
//        python.setOnCheckedChangeListener(this);
//        ruby.setOnCheckedChangeListener(this);
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collection<String> values=hashMap.values();
                StringBuffer sb=new StringBuffer("SELECT ITEM");
                if(values.size() != 0) {
                    sb.delete(0,sb.length());
                    for (String s : values) {
                        sb.append(s);
                        sb.append(" ");
                    }
                }
                textView.setText(sb);
            }
        });

    }

    public void getCheckedItem(View view){
        CheckBox checkBox =(CheckBox)view;
        if(checkBox.isChecked()){
            hashMap.put(checkBox.getId(),checkBox.getText().toString());
        }else{
            hashMap.remove(checkBox.getId());
            if(hashMap.isEmpty())
                textView.setText("ITEM NOT SELECTED");
        }
    }
//    @Override
//    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        if(isChecked){
//            hashMap.put(buttonView.getId(),buttonView.getText().toString());
//        }
//        else {
//            Toast.makeText(getApplicationContext(), "ELSE", Toast.LENGTH_SHORT).show();
//            hashMap.remove(buttonView.getId());
//            if(hashMap.isEmpty())
//                textView.setText("EMPTY");
//        }
//    }
}
