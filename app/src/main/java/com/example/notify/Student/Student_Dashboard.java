package com.example.notify.Student;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import com.example.notify.Admin.Admin_Home;
import com.example.notify.R;
import com.google.android.material.navigation.NavigationView;

public class Student_Dashboard extends AppCompatActivity {

    DrawerLayout dl ;
    NavigationView nv ;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__dashboard);

        dl=findViewById(R.id.student_drawerlayout);
        nv=findViewById(R.id.student_navview);

        toolbar=findViewById(R.id.student_dashboard_toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        dl.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.student_mainframe, new Student_Home()).commit();
            nv.setCheckedItem(R.id.studenthome);
        }
    }
}