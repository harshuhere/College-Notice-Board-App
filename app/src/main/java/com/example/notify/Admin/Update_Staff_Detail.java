package com.example.notify.Admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.notify.R;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Update_Staff_Detail extends Fragment {
    View v;
    EditText edtname,edtemail,edtnumber;
    AutoCompleteTextView edtdepartment;
    Button btnupdate;

    public Update_Staff_Detail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_update__staff__detail, container, false);

        edtname = v.findViewById(R.id.staffupdatename);
        edtemail = v.findViewById(R.id.staffupdateemail);
        edtnumber = v.findViewById(R.id.staffupdateNumber);
        edtdepartment = v.findViewById(R.id.staffupdatedepartment);
        btnupdate = v.findViewById(R.id.staffbtnupdatestaff);

        Bundle b = getArguments();

        String uname = String.valueOf(b.getString("name"));
        String udepart = String.valueOf(b.getString("depart"));
        String unumber = String.valueOf(b.getString("number"));
        String uemail = String.valueOf(b.getString("email"));

        edtname.setText(uname);
        edtemail.setText(uemail);
        edtnumber.setText(unumber);
        edtdepartment.setText(udepart);

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String usname = edtname.getText().toString().trim();
                final String usdepart = edtdepartment.getText().toString().trim();
                final String usnumber = edtnumber.getText().toString().trim();
                final String usemail = edtemail.getText().toString().trim();

                StringRequest sr = new StringRequest(Request.Method.POST, "https://harshpatel26.000webhostapp.com/Notify/update_staff.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(getContext(),"Update Sucessfully..!",Toast.LENGTH_LONG).show();

                        Manage_Staff ms = new Manage_Staff();
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.admin_mainframe,ms).addToBackStack(null).commit();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getContext(), "Update Error", Toast.LENGTH_SHORT).show();

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        HashMap map = new HashMap();

                        map.put("name",usname);
                        map.put("department",usdepart);
                        map.put("number",usnumber);
                        map.put("email",usemail);

                        return map;
                    }
                };

                RequestQueue queue = Volley.newRequestQueue(getContext());
                queue.add(sr);


            }
        });

        return v;
    }
}