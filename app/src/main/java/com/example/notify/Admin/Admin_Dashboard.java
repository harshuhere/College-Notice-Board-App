package com.example.notify.Admin;

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
import android.view.MenuItem;

import android.widget.Toast;

import com.example.notify.R;
import com.google.android.material.navigation.NavigationView;

import java.util.List;


public class Admin_Dashboard extends AppCompatActivity {



    DrawerLayout dl;
    NavigationView nv;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__dashboard);



        dl = findViewById(R.id.admin_drawerlayout);
        nv = findViewById(R.id.admin_navview);

        toolbar = findViewById(R.id.admin_dashboard_toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        dl.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.admin_mainframe, new Admin_Home()).commit();
            nv.setCheckedItem(R.id.adminhome);
        }

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                dl.closeDrawers();
                int id = menuItem.getItemId();

                if (id == R.id.adminhome)
                {
                    Toast.makeText(Admin_Dashboard.this, "Home", Toast.LENGTH_SHORT).show();

                    Admin_Home home = new Admin_Home();
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.admin_mainframe,home).commit();
                }

                if (id == R.id.adminaddnotice)
                {
                    Toast.makeText(Admin_Dashboard.this, "Add Notice", Toast.LENGTH_SHORT).show();

                    Admin_add_notice add = new Admin_add_notice();
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.admin_mainframe,add).commit();
                }

                if (id == R.id.maganestaff)
                {
                    Toast.makeText(Admin_Dashboard.this, "Manage Staff", Toast.LENGTH_SHORT).show();

                    Manage_Staff ms = new Manage_Staff();
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.admin_mainframe,ms).commit();
                }

                if (id == R.id.managestudent)
                {
                    Toast.makeText(Admin_Dashboard.this, "Manage Student", Toast.LENGTH_SHORT).show();

                    Manage_Student ms = new Manage_Student();
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.admin_mainframe,ms).commit();

                }

                return false;
            }
        });




    }

    @Override
    public void onBackPressed() {



        if (dl.isDrawerOpen(GravityCompat.START))
        {
            dl.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
            finishAffinity();
        }
    }



}



