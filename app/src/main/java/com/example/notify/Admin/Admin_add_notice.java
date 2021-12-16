package com.example.notify.Admin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.notify.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Admin_add_notice extends Fragment {

    TextInputEditText edttitle, edtdescription;
    Button btnpost;
    ProgressDialog pd;
    AlertDialog ad;

    public Admin_add_notice() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_admin_add_notice, container, false);
        edttitle = v.findViewById(R.id.edtentertitle);
        edtdescription = v.findViewById(R.id.edtenterdiscription);
        btnpost = v.findViewById(R.id.btnpostnotice);
        pd = new ProgressDialog(getContext());
        pd.setMessage("Please Wait...");


        btnpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String title = edttitle.getText().toString();
                final String description = edtdescription.getText().toString();

                if (edttitle.length() == 0 && edtdescription.length() == 0) {
                    edttitle.setError("Enter Title");
                    edtdescription.setError("Enter Description");
                } else if (edttitle.length() == 0) {
                    edttitle.setError("Enter Title");
                } else if (edtdescription.length() == 0) {
                    edtdescription.setError("Enter Description");
                } else {
                    pd.show();
                    StringRequest sr = new StringRequest(Request.Method.POST, "https://harshpatel26.000webhostapp.com/Notify/addnotice.php", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            pd.cancel();

                            Toast.makeText(getContext(), "Posted", Toast.LENGTH_SHORT).show();

                            // getFragmentManager().beginTransaction().replace(R.id.admin_mainframe, new Admin_add_notice()).commit();

                            Admin_add_notice add = new Admin_add_notice();
                            FragmentManager fm = getFragmentManager();
                            FragmentTransaction ft = fm.beginTransaction();
                            ft.replace(R.id.admin_mainframe, add).addToBackStack(null).commit();


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(), " Not Posted", Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            HashMap map = new HashMap();
                            map.put("title", title);
                            map.put("description", description);

                            return map;
                        }
                    };
                    RequestQueue queue = Volley.newRequestQueue(getContext());
                    queue.add(sr);
                }
            }
        });

        return v;
    }


}

