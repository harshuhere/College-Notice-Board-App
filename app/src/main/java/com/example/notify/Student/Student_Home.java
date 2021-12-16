package com.example.notify.Student;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import com.example.notify.R;
import com.google.android.material.tabs.TabLayout;


public class Student_Home extends Fragment {

    TabLayout tb;
    ViewPager vp;

    public Student_Home() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_student__home, container, false);

            vp=v.findViewById(R.id.studenthomeviewpager);
            setupwithviewpager();

            tb=v.findViewById(R.id.studenthometablayout);
            tb.setupWithViewPager(vp);


        return v;
    }

    private void setupwithviewpager() {

        ViewpagerAdapter va = new ViewpagerAdapter(getFragmentManager());
        va.addfragment(new Student_NewNotice(),"New Notice");
        va.addfragment(new Student_AllNotice(),"All Notice");
        va.addfragment(new Student_Starred(),"Satrred");

        vp.setAdapter(va);
    }


}
