package com.example.notify.Admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.notify.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Admin_newnotice extends Fragment {

    ListView lv;
    List<ModelViewNewNoticeAdmin> list;
    ProgressDialog pd;

    public Admin_newnotice() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_admin_newnotice, container, false);

        lv = v.findViewById(R.id.adminallnoticelistview);
        list = new ArrayList<>();

        pd = new ProgressDialog(getContext());
        pd.setCancelable(false);
        pd.setMessage("Loading...");

        pd.show();


        StringRequest sr = new StringRequest(Request.Method.POST, "https://harshpatel26.000webhostapp.com/Notify/student_view_new_notice.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);

                        String ti = object.getString("title");
                        String di = object.getString("description");

                        ModelViewNewNoticeAdmin m = new ModelViewNewNoticeAdmin();
                        m.setTitle(ti);
                        m.setDiscr(di);

                        list.add(m);

                        pd.cancel();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                MyAdapter adapter = new MyAdapter();
                lv.setAdapter(adapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.cancel();

                Toast.makeText(getContext(), "No Internet", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(sr);


        return v;
    }

    class MyAdapter extends BaseAdapter {


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
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.design_view_admin_newnotice, parent, false);

            final TextView atitle = convertView.findViewById(R.id.Atxttitle);
            final TextView adisc = convertView.findViewById(R.id.Atxtdiscription);

            final ModelViewNewNoticeAdmin m = list.get(position);
            atitle.setText(m.title);
            adisc.setText(m.discr);

            CheckBox chk = convertView.findViewById(R.id.favourite);
            chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    Toast.makeText(getContext(), "Checked", Toast.LENGTH_SHORT).show();

                }
            });


            return convertView;
        }
    }
}
