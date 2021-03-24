package com.example.domainextractor.Chipfragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.domainextractor.Adapters.MyAdapters;
import com.example.domainextractor.CustomnavFrag.ProjectFragment;
import com.example.domainextractor.CustomnavFrag.ServerFragment;
import com.example.domainextractor.CustomnavFrag.apiFragment;
import com.example.domainextractor.MainActivity;
import com.example.domainextractor.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;


public class Chip_SearchFragment extends Fragment {
    Integer image = R.id.sideImage;
     ListView Domain;
    MainActivity mainActivity;
    public Chip_SearchFragment(MainActivity mainActivity) {

    this.mainActivity=mainActivity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_chip__search, container, false);

        // MyAdapters myAdapters = new MyAdapters(this,R.layout.row,Maintitle, Subtitle,images,1);
        MainActivity.main.setText("Search");
        MainActivity.logo.setVisibility(View.VISIBLE);
        Domain=view.findViewById(R.id.FragmentSearchListView);


        MainActivity.ID = 0;
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();

        MainActivity.logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              MainActivity.main.setVisibility(View.INVISIBLE);
              MainActivity.ref.setVisibility(View.VISIBLE);

            }
        });





        RequestQueue requestQueue =  Volley.newRequestQueue(getContext());

        String url = "https://webspidy.in/sam/function.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    ProgressBar progressBar= view.findViewById(R.id.progressBar2);
                    TextView textView= view.findViewById(R.id.wait);
                    textView.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                    JSONArray jsonArray  = new JSONArray(response);
                    int i=jsonArray.length()-1;
                    while (i>=0){
                        JSONObject object =jsonArray.getJSONObject(i);
                        String type = object.getString("TYPE");
                        String VAL = object.getString("VAL");
                        list.add(type);
                        list2.add(VAL);
                        i--;
                        if (i==0)
                        {


                        }
                    }




                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "error is"+e, Toast.LENGTH_SHORT).show();
                }


                MyAdapters myAdapters = new MyAdapters(mainActivity,
                        R.layout.row,
                        list,
                        list2,
                        image,
                        1);
                Domain.setAdapter(myAdapters);

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

               Toast.makeText(getActivity(), "error....", Toast.LENGTH_SHORT).show();

            }
        });

  requestQueue.add(stringRequest);











        return  view;

    }





}