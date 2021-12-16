package com.example.notify.Admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import com.example.notify.R;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class Admin_Home extends Fragment {

    ViewPager viewpager;
    TabLayout tablayout;


    public Admin_Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_admin__home, container, false);

        viewpager=v.findViewById(R.id.adminviewpager);
        setupwithviewpager();

        tablayout=v.findViewById(R.id.admintablayout);
        tablayout.setupWithViewPager(viewpager);

    /*    if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().replace(R.id.dashboardmainframe, new Admin_Home()).commit();
          //  nv.setCheckedItem(R.id.adminhome);
        }*/


        return v;


    }
    private void setupwithviewpager()
    {
        ViewpagerAdapter va = new ViewpagerAdapter(getFragmentManager());
        va.addfragment(new Admin_newnotice(),"Notice");
        va.addfragment(new Admin_starred(),"Starred");

        viewpager.setAdapter(va);
    }


}
