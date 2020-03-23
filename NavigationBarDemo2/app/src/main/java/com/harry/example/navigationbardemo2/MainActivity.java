package com.harry.example.navigationbardemo2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import static android.view.Gravity.START;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("HELLO");
        drawerLayout=findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_open);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        final NavigationView navigationView=findViewById(R.id.navigation_view);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MessageFragment());
        navigationView.setCheckedItem(R.id.nav_msg);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem){
                switch (menuItem.getItemId()){
                    case R.id.nav_chat: addFragment(new ChatFragment());
                                        navigationView.setCheckedItem(menuItem.getItemId());
                                        break;
                    case R.id.nav_msg:addFragment(new MessageFragment());
                        navigationView.setCheckedItem(menuItem.getItemId());
                                        break;
                    case R.id.nav_profile:addFragment(new ProfileFragment());
                        navigationView.setCheckedItem(menuItem.getItemId());
                                        break;
                    case R.id.nav_send:
                        Toast.makeText(getApplicationContext(),"SEND",Toast.LENGTH_LONG).show();
                        navigationView.setCheckedItem(menuItem.getItemId());
                        break;
                    case R.id.nav_share:
                    Toast.makeText(getApplicationContext(),"SHARE",Toast.LENGTH_LONG).show();
                        navigationView.setCheckedItem(menuItem.getItemId());
                    break;
                    default:break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
    private void addFragment(Fragment f){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,f);
        fragmentTransaction.commit();
    }
}
