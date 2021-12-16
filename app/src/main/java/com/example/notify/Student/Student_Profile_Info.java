package com.example.notify.Student;

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
public class Student_Profile_Info extends Fragment  {

    Button btnnext;


    public Student_Profile_Info() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_student__profile__info, container, false);

        btnnext = v.findViewById(R.id.spbtnnext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), Student_Dashboard.class);
                startActivity(i);
            }
        });

        return v;
    }



}