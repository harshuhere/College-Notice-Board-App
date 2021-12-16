package com.example.notify.Admin;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.notify.R;



/**
 * A simple {@link Fragment} subclass.
 */
public class Admin_Profile_info extends Fragment {
    Button btn;


    public Admin_Profile_info() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_admin__profile_info, container, false);

        btn = v.findViewById(R.id.apbtnnext);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),Admin_Dashboard.class);
                startActivity(i);
            }
        });


        return v;
    }
}