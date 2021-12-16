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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Add_Student extends Fragment {


    int smaxid = 0;
    Add_Student_Model smodel ;
    ProgressDialog spd;
    EditText sedtname,sedtnumber,sedtemail;
    Button sbtnadd;
    AutoCompleteTextView stxtdepartment;

    String[] sdepartment = {"Information Technologies","Mechanical Engineering","Electrical Engineering","Android Development","IOS Development","Civil Engineering","Ethical Hacking",".net","php","Web Development","Computer Engineering"};

    public Add_Student() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add__student, container, false);

        stxtdepartment = v.findViewById(R.id.studententerdepartment);
        stxtdepartment.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,sdepartment));

        sedtname = v.findViewById(R.id.studententername);
        sedtemail = v.findViewById(R.id.studententeremail);
        sedtnumber = v.findViewById(R.id.studententernumber);
        sbtnadd = v.findViewById(R.id.studentbtnaddstudent);

        spd = new ProgressDialog(getContext());
        spd.setMessage("Adding student...");

        sbtnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name = sedtname.getText().toString();
                final String department = stxtdepartment.getText().toString();
                final String number = sedtnumber.getText().toString();
                final String email = sedtemail.getText().toString();

                if (sedtname.length() == 0 && sedtnumber.length() == 0 && sedtemail.length() == 0 && stxtdepartment.length() == 0)
                {
                    sedtemail.setError("Enter email");
                    sedtname.setError("Enter name");
                    sedtnumber.setError("Enter number");
                    stxtdepartment.setError("Enter department");
                }
                else if (sedtname.length() == 0)
                {
                    sedtname.setError("Enter name");
                }
                else if (sedtnumber.length() == 0)
                {
                    sedtnumber.setError("Enter number");
                }
                else if (sedtemail.length() == 0 )
                {
                    sedtemail.setError("Enter email");
                }
                else if (stxtdepartment.length() == 0)
                {
                    stxtdepartment.setError("Enter department");
                }
                else
                {
                    spd.show();

                    StringRequest sr = new StringRequest(Request.Method.POST, "https://harshpatel26.000webhostapp.com/Notify/student_list.php", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            spd.cancel();

                            Toast.makeText(getContext(), "Added", Toast.LENGTH_SHORT).show();

                            Add_Student as = new Add_Student();
                            FragmentManager fm = getFragmentManager();
                            FragmentTransaction ft = fm.beginTransaction();
                            ft.replace(R.id.admin_mainframe,as).addToBackStack(null).commit();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            spd.cancel();
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