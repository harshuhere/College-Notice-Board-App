package com.example.notify.Admin;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
public class Add_Staff extends Fragment {


    EditText edtname,edtnumber,edtemail,edtpassword;
    AutoCompleteTextView txtdepartment;
    Button btnadd;
    ProgressDialog pd;

    String[] department = {"Information Technologies","Mechanical Engineering","Electrical Engineering","Android Development","IOS Development","Civil Engineering","Ethical Hacking",".net","php","Web Development","Computer Engineering"};


    public Add_Staff() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_add__staff, container, false);

        txtdepartment = v.findViewById(R.id.staffenterdepartment);
        txtdepartment.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,department));

        edtname = v.findViewById(R.id.staffentername);
        edtemail = v.findViewById(R.id.staffenteremail);
        edtnumber = v.findViewById(R.id.staffenterNumber);
        edtpassword = v.findViewById(R.id.staffenterpassword);
        btnadd = v.findViewById(R.id.staffbtnaddstaff);

        pd = new ProgressDialog(getContext());
        pd.setMessage("Adding Staff...");


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name = edtname.getText().toString();
                final String department = txtdepartment.getText().toString();
                final String number = edtnumber.getText().toString();
                final String email = edtemail.getText().toString();
                final String password = edtpassword.getText().toString();

                if (edtname.length() == 0 && edtnumber.length() == 0 && edtemail.length() == 0 && txtdepartment.length() == 0 && edtpassword.length() == 0)
                {
                    edtemail.setError("Enter email");
                    edtname.setError("Enter name");
                    edtnumber.setError("Enter number");
                    edtpassword.setError("Enter password");
                    txtdepartment.setError("Enter department");
                }
                else if (edtname.length() == 0)
                {
                    edtname.setError("Enter name");
                }
                else if (edtnumber.length() == 0)
                {
                    edtnumber.setError("Enter number");
                }
                else if (edtemail.length() == 0 )
                {
                    edtemail.setError("Enter email");
                }
                else if (txtdepartment.length() == 0)
                {
                    txtdepartment.setError("Enter department");
                }
                else if (edtpassword.length()==0)
                {
                    edtpassword.setError("Enter password");
                }
                else
                {
                    pd.show();

                    StringRequest sr = new StringRequest(Request.Method.POST, "https://harshpatel26.000webhostapp.com/Notify/staff_list.php", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            pd.cancel();

                            Toast.makeText(getContext(), "Added", Toast.LENGTH_SHORT).show();

                            Add_Staff as = new Add_Staff();
                            FragmentManager fm = getFragmentManager();
                            FragmentTransaction ft = fm.beginTransaction();
                            ft.replace(R.id.admin_mainframe,as).addToBackStack(null).commit();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            pd.cancel();
                            Toast.makeText(getContext(), "Error try again", Toast.LENGTH_SHORT).show();
                        }
                    })
                    {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap map = new HashMap();

                            map.put("name",name);
                            map.put("department",department);
                            map.put("number",number);
                            map.put("email",email);
                            map.put("password",password);


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