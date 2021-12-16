package com.example.notify.Admin;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.notify.R;


public class AdminSignin extends Fragment {

    Button btngenerateotp;
    View v;
    EditText edtmobile;
    Spinner spinner;


    public AdminSignin() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        v = inflater.inflate(R.layout.fragment_admin_signin, container, false);
        edtmobile = v.findViewById(R.id.Ainedtmobilenumber);

        spinner = v.findViewById(R.id.AspinnerCountries);
        spinner.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, AdminCountryData.countryAreaCodes));


        btngenerateotp = v.findViewById(R.id.Ainbtngenerateotp);

        btngenerateotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String code = AdminCountryData.countryAreaCodes[spinner.getSelectedItemPosition()];
                String number = edtmobile.getText().toString().trim();

                if (number.isEmpty() || number.length() < 10) {
                    edtmobile.setError("Enter Valid Number");
                    edtmobile.requestFocus();
                    return;
                }

                String phoneNumber = code + number;

                Admin_enter_otp enterotp = new Admin_enter_otp();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                Bundle b = new Bundle();
                b.putString("phonenumber",phoneNumber);
                enterotp.setArguments(b);

                ft.replace(R.id.mframe,enterotp).addToBackStack(number).commit();

            }
        });


        return v;
    }


}
