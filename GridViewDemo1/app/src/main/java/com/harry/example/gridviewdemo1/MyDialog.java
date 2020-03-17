package com.harry.example.gridviewdemo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MyDialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dialog);
        Intent intent=getIntent();
        int image_resource=intent.getIntExtra("image_resource",-1);
        String country_name=intent.getStringExtra("country_name");
        TextView textView=findViewById(R.id.dialog_textiew);
        ImageView imageView=findViewById(R.id.dialog_imageView);
        textView.setText(country_name);
        imageView.setImageResource(image_resource);
    }

    public void closeDialog(View view){
        finish();
    }
}
