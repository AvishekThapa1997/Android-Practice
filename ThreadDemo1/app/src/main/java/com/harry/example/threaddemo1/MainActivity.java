package com.harry.example.threaddemo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import download.image.thread.DownloadImageThread;

public class MainActivity<Editext> extends AppCompatActivity implements View.OnClickListener {

    private String[] urls;
    private  String[] list_names;
    private ListView listView;
    private TextView image_url;
    private Button download;
    private LinearLayout linearLayout;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        urls=getResources().getStringArray(R.array.urls);
        list_names=getResources().getStringArray(R.array.list);
        image_url=findViewById(R.id.url);
        download=findViewById(R.id.download);
        linearLayout=findViewById(R.id.linear_layout);
        progressBar=findViewById(R.id.progress_bar);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,list_names);
        listView=findViewById(R.id.list_view);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                image_url.setText(urls[position]);
            }
        });
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        download.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String url=image_url.getText().toString();
        if (validateUrl(url)){
              Thread thread=new Thread(new DownloadImageThread(this,this,url,linearLayout));
              thread.start();
        }else{
            Toast.makeText(getApplicationContext(),"INVALID DATA",Toast.LENGTH_LONG).show();
        }
    }
    private boolean validateUrl(String url){
        return url != null && !url.equals(" ") && !url.isEmpty();
    }
}
