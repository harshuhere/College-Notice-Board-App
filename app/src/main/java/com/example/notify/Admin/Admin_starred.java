package com.example.notify.Admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.notify.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Admin_starred extends Fragment {


    View v;

    public Admin_starred() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_admin_starred, container, false);


        return v;
    }
}
