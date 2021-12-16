package com.example.notify.Admin;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
public class Manage_Staff extends Fragment {

    FloatingActionButton fbtn;
    ListView lv;
    List<Add_Staff_Model>list;
    FrameLayout fl;
    ProgressDialog pd;


    public Manage_Staff() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_manage_staff, container, false);

        lv=v.findViewById(R.id.stafflistview);
        list = new ArrayList<>();

        fl = v.findViewById(R.id.staffframe);

        pd = new ProgressDialog(getContext());
        pd.setCancelable(false);
        pd.setMessage("Loading...");

        pd.show();

        StringRequest sr = new StringRequest(Request.Method.POST, "https://harshpatel26.000webhostapp.com/Notify/view_staff_list.php", new Response.Listener<String>() {
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

                        Add_Staff_Model model = new Add_Staff_Model();

                        model.setName(name);
                        model.setDepartment(department);
                        model.setNumber(number);
                        model.setEmail(email);

                        list.add(model);

                        pd.cancel();
                    }


                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

                StaffMyAdapter adapter = new StaffMyAdapter();
                lv.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                pd.cancel();
                Toast.makeText(getContext(), "No internet", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(sr);



        fbtn = v.findViewById(R.id.floatingbtnaddstaff);

        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add_Staff as = new Add_Staff();
                fl.removeAllViews();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.staffframe, as).addToBackStack(null).commit();
            }
        });

        return v;
    }

    class StaffMyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return list.size();
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
        public View getView(final int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.design_staff_list,parent,false);

            final TextView name = convertView.findViewById(R.id.staffname);
            final TextView department = convertView.findViewById(R.id.staffdepartment);
            final TextView number = convertView.findViewById(R.id.staffnumber);
            final TextView email = convertView.findViewById(R.id.staffemail);
            final TextView optiontxt = convertView.findViewById(R.id.txtoption);

            optiontxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Add_Staff_Model model = list.get(position);
                    PopupMenu popupMenu = new PopupMenu(getContext(),optiontxt);
                    popupMenu.inflate(R.menu.staff_option_menu);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            switch (item.getItemId())
                            {
                                case R.id.staffupdate:

                                    String uname = name.getText().toString();
                                    String depart = department.getText().toString();
                                    String unumber = number.getText().toString();
                                    String uemail = email.getText().toString();

                                    Update_Staff_Detail sd = new Update_Staff_Detail();
                                    FragmentManager fm =getFragmentManager();
                                    FragmentTransaction ft = fm.beginTransaction();

                                    Bundle b = new Bundle();
                                    b.putString("name", uname);
                                    b.putString("depart",depart);
                                    b.putString("number",unumber);
                                    b.putString("email",uemail);

                                    sd.setArguments(b);

                                    ft.replace(R.id.admin_mainframe,sd).addToBackStack(null).commit();

                                    Toast.makeText(getContext(), "Updated", Toast.LENGTH_SHORT).show();
                                    break;

                                case R.id.staffdelete:
                                    Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT).show();
                                    break;
                            }

                            return false;
                        }
                    });
                    popupMenu.show();
                }
            });

            Add_Staff_Model model = list.get(position);

            name.setText(model.getName());
            department.setText(model.getDepartment());
            number.setText(model.getNumber());
            email.setText(model.getEmail());


            return convertView;
        }
    }


}