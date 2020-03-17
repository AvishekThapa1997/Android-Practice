package com.harry.example.radiobuttondemo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RadioGroup radioGroup=findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.male:
                        RadioButton radioButton_male=findViewById(R.id.male);
                        Toast.makeText(getApplicationContext(),radioButton_male.getContentDescription(),Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.female:
                        RadioButton radioButton_female=findViewById(R.id.female);
                        Toast.makeText(getApplicationContext(),radioButton_female.getContentDescription(),Toast.LENGTH_SHORT).show();
                        break;
                    default:break;
                }
            }
        });

    }
}
