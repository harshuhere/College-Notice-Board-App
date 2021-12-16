package com.example.notify.Student;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.notify.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class StudentSignin extends Fragment {

    Button btngenerateotp;
    EditText edtmobilenumber;
    CircleImageView profileimage;
    Spinner spinner;
    View v;


    public StudentSignin() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_student_signin, container, false);


        edtmobilenumber = v.findViewById(R.id.sinedtmobilenumber);

        spinner = v.findViewById(R.id.sspinnerCountries);
        spinner.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, StudentCountryData.countryAreaCodes));

        btngenerateotp = v.findViewById(R.id.Sinbtngenerateotp);

        btngenerateotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String code = StudentCountryData.countryAreaCodes[spinner.getSelectedItemPosition()];
                String number = edtmobilenumber.getText().toString().trim();

                if (number.isEmpty() || number.length() < 10) {
                    edtmobilenumber.setError("Enter Valid Number");
                    edtmobilenumber.requestFocus();
                    return;
                }

                String sphoneNumber = code + number;

                Student_EnterOTP sotp = new Student_EnterOTP();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                Bundle sb = new Bundle();
                sb.putString("sphonenumber", sphoneNumber);
                sotp.setArguments(sb);

                ft.replace(R.id.mframe, sotp).addToBackStack(number).commit();


            }
        });


        return v;

    }


}
