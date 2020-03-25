package com.harry.example.fulltoolbar1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import maintoolbar_menu_activity.AboutUS;
import maintoolbar_menu_activity.Settings;
import maintoolbar_menu_activity.Users;
import navigationdrawer_menu_fragments.AllMailFragment;
import navigationdrawer_menu_fragments.DraftsFragment;
import navigationdrawer_menu_fragments.InboxFragment;
import navigationdrawer_menu_fragments.SendFragment;
import navigationdrawer_menu_fragments.SpamFragment;
import navigationdrawer_menu_fragments.StarredFragment;
import navigationdrawer_menu_fragments.TrashFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private boolean drawer_closed=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);
        toolbar=findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.naviation_view);
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.settings:loadActivity(Settings.class);
            break;
            case R.id.users:loadActivity(Users.class);
            break;
            case R.id.about_us:loadActivity(AboutUS.class);
            break;
        }
        return true;
    }
    private void loadActivity(Class activity_class){
        Intent intent=new Intent(getApplicationContext(),activity_class);
        startActivity(intent);
    }
    private void loadFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.inbox:loadFragment(new InboxFragment());
                break;
            case R.id.starred:loadFragment(new StarredFragment());
                break;
            case R.id.send:loadFragment(new SendFragment());
                break;
            case R.id.drafts:loadFragment(new DraftsFragment());
                break;
            case R.id.all_mail:loadFragment(new AllMailFragment());
                break;
            case R.id.trash:loadFragment(new TrashFragment());
                break;
            case R.id.spam:loadFragment(new SpamFragment());
                break;
            case android.R.id.home:finish();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout!=null && drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else if(drawer_closed){
            super.onBackPressed();
        } else if(drawerLayout != null && !(drawerLayout.isDrawerOpen(GravityCompat.START))){
            Toast.makeText(getApplicationContext(),"Press One More Time To Exit",Toast.LENGTH_LONG).show();
            drawer_closed=true;
        }
    }
}
