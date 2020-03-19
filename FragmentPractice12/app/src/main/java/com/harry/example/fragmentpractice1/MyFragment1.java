package com.harry.example.fragmentpractice1;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyFragment1 extends Fragment implements  View.OnClickListener{
    private Communicator communicator;
    private  final static String TAG="FRAGMENTS";
    static int count;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach");
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       Log.i(TAG, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        return inflater.inflate(R.layout.fragment_layout1,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Button button=getActivity().findViewById(R.id.show);
        communicator=(Communicator) getActivity();
        Toast.makeText(getContext(),String.valueOf(button == null),Toast.LENGTH_LONG).show();
        button.setOnClickListener(this);
        Log.i(TAG, "onActivityCreated: ");
    }
    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState: ");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }
    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "onDetach: ");
    }

    @Override
    public void onClick(View v){
        if (communicator != null){
            count++;
            communicator.respond(String.valueOf(count));
        }
    }
}
