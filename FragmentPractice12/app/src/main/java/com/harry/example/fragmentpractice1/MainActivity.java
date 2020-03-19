package com.harry.example.fragmentpractice1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Communicator {

    private static final String TAG="FRAGMENTS";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate Activity");
        MyFragment1 myFragment1 =new MyFragment1();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.framelayout1,myFragment1,"My Fragment");
        fragmentTransaction.commit();

        MyFragment2 myFragment2=new MyFragment2();
        FragmentManager fragmentManager1=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction1=fragmentManager1.beginTransaction();
        fragmentTransaction1.add(R.id.framelayout2,myFragment2,"My Fragment2");
        fragmentTransaction1.commit();
    }
@Override
public void onAttachFragment(@NonNull Fragment fragment) {
    super.onAttachFragment(fragment);
    Log.i(TAG, "onAttachFragment Activity");
}

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState Activity");
    }

    @Override
    protected void onStart(){
        Log.i(TAG, "onStart Activity");
        super.onStart();
    }

    @Override
    protected void onResume(){
        Log.i(TAG, "onResume Activity");
        super.onResume();
    }

    @Override
    protected void onPause(){
        Log.i(TAG, "onPause Activity");
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        Log.i(TAG, "onSaveInstanceState Activity");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop(){
        Log.i(TAG, "onStop Activity");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart Activity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy Activity");
    }

    @Override
    public void respond(String data) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        MyFragment2 myFragment2=(MyFragment2) fragmentManager.findFragmentById(R.id.framelayout2);
        myFragment2.changedText(data);

//        MyFragment2 fragment=(MyFragment2) fragmentManager.findFragmentById(R.id.framelayout2);
//        Toast.makeText(getApplicationContext(),String.valueOf(fragment == null),Toast.LENGTH_LONG).show();
    }
}
