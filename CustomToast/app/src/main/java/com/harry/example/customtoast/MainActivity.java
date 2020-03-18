package com.harry.example.customtoast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                LayoutInflater layoutInflater=getLayoutInflater();
                LinearLayout linearLayout=findViewById(R.id.linear_layout);
                View view=layoutInflater.inflate(R.layout.custom_toast,linearLayout,true);
                Toast toast=Toast.makeText(getApplicationContext(),"HEllo",Toast.LENGTH_LONG);
                TextView textView=view.findViewById(R.id.textView);
                textView.setText("CUSTOM TOAST");
                toast.setView(view);
                toast.show();
            }
        });
    }
}
