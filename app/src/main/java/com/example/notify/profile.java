package com.example.notify;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.notify.Admin.AdminSignin;
import com.example.notify.Student.StudentSignin;


/**
 * A simple {@link Fragment} subclass.
 */
public class profile extends Fragment {

    ImageView imgadmin,imgstudent;
    View v;

    public profile() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_profile, container, false);

        imgadmin=v.findViewById(R.id.imgadmin);
        imgstudent=v.findViewById(R.id.imgstudent);

        imgadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Admin", Toast.LENGTH_SHORT).show();

                AdminSignin asi=new AdminSignin();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.mframe,asi).addToBackStack(null).commit();
            }
        });


        imgstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "Student", Toast.LENGTH_SHORT).show();

               StudentSignin sin = new StudentSignin();
               FragmentManager fm = getFragmentManager();
               FragmentTransaction ft = fm.beginTransaction();
               ft.replace(R.id.mframe,sin).addToBackStack(null).commit();


            }
        });

        return v;
    }


}
