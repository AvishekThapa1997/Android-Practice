package com.harry.example.spinnerdemo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String[] country_names;
    private Spinner spinner;
    private ArrayList<Country> countries;
    private int[] country_images={R.drawable.afghanistan,R.drawable.albania,R.drawable.bangladesh,R.drawable.brazil,R.drawable.finland,R.drawable.guatemala,R.drawable.nepal,R.drawable.veitnam,R.drawable.yemen,R.drawable.zambia};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner=findViewById(R.id.spinner);
        Resources resources=getResources();
        country_names=resources.getStringArray(R.array.country_name);
        countries=new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<country_images.length;i++){
                    Country country=new Country(country_images[i],country_names[i]);
                    countries.add(country);
                }
            }
        }).start();
        CustomAdapter customAdapter=new CustomAdapter(getApplicationContext(),countries);
        //ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,days);
        spinner.setAdapter(customAdapter);
        new Thread(new Runnable() {
            @Override
            public void run() {
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        ImageView imageView=view.findViewById(R.id.imageView);
                        Country country=(Country) imageView.getTag();
                        Intent intent = new Intent(getApplicationContext(),AlertActivity.class);
                        intent.putExtra("country_name",country.country_name);
                        startActivity(intent);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        }).start();

    }
    private class CustomAdapter extends BaseAdapter{
        Context context;
        private ArrayList<MainActivity.Country> countries;
        private CustomAdapter(Context context,ArrayList<MainActivity.Country> countries){
            this.context=context;
            this.countries=countries;
        }
        @Override
        public int getCount() {
            return countries.size();
        }

        @Override
        public MainActivity.Country getItem(int position) {
            return countries.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            Toast.makeText(getApplicationContext(),parent.getClass().getName(),Toast.LENGTH_LONG).show();
            View view=convertView;
            if(convertView == null){
                LayoutInflater layoutInflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                try {
                    view=layoutInflater.inflate(R.layout.custom_resource,parent,false);
                }catch (NullPointerException ne){ne.printStackTrace();}
            }
            MainActivity.Country country=getItem(position);
            ImageView imageView=view.findViewById(R.id.imageView);
            TextView textView=view.findViewById(R.id.textView);
            imageView.setImageResource(country.image_resource);
            textView.setText(country.country_name);
            imageView.setTag(country);
            return view;
        }

    }
    private static class Country{
        int image_resource;
        String country_name;
        Country(int image_resource,String country_name) {
            this.image_resource = image_resource;
            this.country_name = country_name;
        }
    }
}
