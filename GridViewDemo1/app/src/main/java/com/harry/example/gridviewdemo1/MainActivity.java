package com.harry.example.gridviewdemo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragAndDropPermissions;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private int[] image_resource={R.drawable.afghanistan,R.drawable.albania,R.drawable.bangladesh,R.drawable.brazil,R.drawable.finland,R.drawable.guatemala,R.drawable.nepal,R.drawable.veitnam,R.drawable.yemen,R.drawable.zambia};
    private String[] country_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView=findViewById(R.id.gridView);
        Resources resources=getResources();
        country_name=resources.getStringArray(R.array.countrynames);
        final CustomAdapter customAdapter=new CustomAdapter(getApplicationContext(),country_name,image_resource);
        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),MyDialog.class);

               // Country country=customAdapter.countryArrayList.get(position); //This is another way to show

                //==============This is one way to show in dialog
                CustomAdapter.MyHolder myHolder=(CustomAdapter.MyHolder)view.getTag();
                Country country=(Country)myHolder.imageView.getTag();
                intent.putExtra("country_name",country.getCountry_name());
                intent.putExtra("image_resource",country.getImage_resource());
                startActivity(intent);
            }
        });
    }

    private class CustomAdapter extends BaseAdapter{
        ArrayList<MainActivity.Country> countryArrayList;
        private Context context;
            public CustomAdapter(Context context, String[] country_names, int[] image_resource){
                this.context=context;
                countryArrayList=new ArrayList<MainActivity.Country>();
                for(int i=0;i<country_names.length;i++){
                    Country country=new Country(country_names[i],image_resource[i]);
                    countryArrayList.add(country);
                }
            }
        @Override
        public int getCount() {
                return countryArrayList.size();
        }

        @Override
        public MainActivity.Country getItem(int position) {
                return countryArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
                View view=convertView;
                MyHolder myHolder=null;
                if(view == null){
                    LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view=layoutInflater.inflate(R.layout.custom_resource,parent,false);
                    myHolder=new MyHolder(view);
                    view.setTag(myHolder);
                }
                myHolder=(MyHolder)view.getTag();
            MainActivity.Country country=getItem(position);
            myHolder.imageView.setImageResource(country.getImage_resource());
            myHolder.textView.setText(country.getCountry_name());
            myHolder.imageView.setTag(country);
            return view;
        }

        private class MyHolder{
                public TextView textView;
                public ImageView imageView;
                public MyHolder(){
                }
                public MyHolder(View view){
                    textView=view.findViewById(R.id.textView);
                    imageView=view.findViewById(R.id.imageView);
                }
        }

    }

    private class Country{
        private String country_name;
        private int image_resource;
        public Country(String country_name,int image_resource){
            this.country_name=country_name;
            this.image_resource=image_resource;
        }
        public String getCountry_name(){
            return country_name;
        }
        public int getImage_resource(){
            return image_resource;
        }
    }

}
