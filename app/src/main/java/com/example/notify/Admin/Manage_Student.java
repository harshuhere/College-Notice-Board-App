package com.example.notify.Admin;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.notify.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Manage_Student extends Fragment {

    FloatingActionButton sfbtn;
    ListView slv;
    List<Add_Student_Model>slist;
    FrameLayout sfl;
    ProgressDialog spd;


    public Manage_Student() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_manage__student, container, false);

        sfbtn = v.findViewById(R.id.floatingbtnaddstudent);
        sfl = v.findViewById(R.id.studentframe);

        slv = v.findViewById(R.id.studentlistview);
        slist = new ArrayList<>();

        spd = new ProgressDialog(getContext());
        spd.setCancelable(false);
        spd.setMessage("Loading...");

        spd.show();

        StringRequest sr = new StringRequest(Request.Method.POST, "https://harshpatel26.000webhostapp.com/Notify/view_student_list.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array = new JSONArray(response);
                    for (int i=0 ; i<array.length(); i++)
                    {
                        JSONObject object = array.getJSONObject(i);

                        String name = object.getString("name");
                        String department = object.getString("department");
                        String number = object.getString("number");
                        String email = object.getString("email");

                        Add_Student_Model model = new Add_Student_Model();

                        model.setName(name);
                        model.setDepartment(department);
                        model.setNumber(number);
                        model.setEmail(email);

                        slist.add(model);

                        spd.cancel();
                    }


                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

                StudentMyAdapter adapter = new StudentMyAdapter();
                slv.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                spd.cancel();
                Toast.makeText(getContext(), "No internet", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(sr);

        sfbtn = v.findViewById(R.id.floatingbtnaddstudent);

        sfbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add_Student as = new Add_Student();
                sfl.removeAllViews();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.studentframe, as).addToBackStack(null).commit();
            }
        });

        return v;
    }

    class StudentMyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return slist.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.design_student_list,parent,false);

            TextView name = convertView.findViewById(R.id.studentname);
            TextView department = convertView.findViewById(R.id.studentdepartment);
            TextView number = convertView.findViewById(R.id.studentnumber);
            TextView email = convertView.findViewById(R.id.studentemail);

            Add_Student_Model model = slist.get(position);

            name.setText(model.getName());
            department.setText(model.getDepartment());
            number.setText(model.getNumber());
            email.setText(model.getEmail());


            return convertView;
        }
    }
}

