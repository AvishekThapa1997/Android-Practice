package com.harry.example.toolbardemo1;

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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private boolean close=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Material Design");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigation_view);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer_menu,menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Toast.makeText(getApplicationContext(),"Prepare Menu",Toast.LENGTH_LONG).show();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
           case R.id.settings:Toast.makeText(getApplicationContext(),"Settings",Toast.LENGTH_LONG).show();
            break;
            case R.id.users:Toast.makeText(getApplicationContext(),"Users",Toast.LENGTH_LONG).show();
            break;
            case R.id.about_us:Toast.makeText(getApplicationContext(),"About Us",Toast.LENGTH_LONG).show();
            break;
            case R.id.cart:Toast.makeText(getApplicationContext(),"Cart Selected",Toast.LENGTH_LONG).show();
            break;
            case R.id.search:Toast.makeText(getApplicationContext(),"Search Selected",Toast.LENGTH_LONG).show();
            break;
            case android.R.id.home:Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_LONG).show();
            finish();
            default:break;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){
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
            default:break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    private void loadFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout != null){
            if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                drawerLayout.closeDrawer(GravityCompat.START);
            } else if(close){
                super.onBackPressed();
            }
            else if(!drawerLayout.isDrawerOpen(GravityCompat.START)){
                Toast.makeText(getApplicationContext(),"Press One More Time To Exit",Toast.LENGTH_LONG).show();
                close=true;
            }

        }
    }
}
