package com.example.notify.Student;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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


public class Student_NewNotice extends Fragment {

    ListView lv;
    List<ModelViewNewNoticeStudent>list;

    public Student_NewNotice() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_student__new_notice, container, false);

        lv=v.findViewById(R.id.studentnewnoticelistview);
        list=new ArrayList<>();

        StringRequest sr = new StringRequest(Request.Method.POST, "https://harshpatel26.000webhostapp.com/Notify/student_view_new_notice.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array = new JSONArray(response);
                    for (int i=0 ; i<array.length(); i++)
                    {
                        JSONObject object = array.getJSONObject(i);

                        String t = object.getString("title");
                        String d = object.getString("description");

                        ModelViewNewNoticeStudent m = new ModelViewNewNoticeStudent();
                        m.setTitle(t);
                        m.setDisc(d);

                        list.add(m);
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

                Toast.makeText(getContext(), "No Internet", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(sr);

        return v;
    }

    class MyAdapter extends BaseAdapter
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
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.design_view_student_newnotice,parent,false);

            TextView title = convertView.findViewById(R.id.txttitle);
            TextView disc = convertView.findViewById(R.id.txtdiscription);

            ModelViewNewNoticeStudent m = list.get(position);
            title.setText(m.title);
            disc.setText(m.disc);


            return convertView;
        }
    }


}
