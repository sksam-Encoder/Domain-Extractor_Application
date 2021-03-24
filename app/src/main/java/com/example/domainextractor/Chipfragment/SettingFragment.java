package com.example.domainextractor.Chipfragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.domainextractor.Adapters.MyAdapters;
import com.example.domainextractor.MainActivity;
import com.example.domainextractor.R;

import java.util.ArrayList;
import java.util.Objects;


public class SettingFragment extends Fragment {


    String uname;

    public SettingFragment(MainActivity mainActivity) {
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_setting, container, false);
        ArrayList<String> arrayList =new ArrayList<>();
        arrayList.add("Logged In as"+uname);
        arrayList.add("Address book");
        arrayList.add("Log out");
        arrayList.add("Onetouch 2fa");
        arrayList.add("Setup this device");
        arrayList.add("Support");
        arrayList.add("Get help");
        arrayList.add("Live chat");
        TextView textView= getActivity().findViewById(R.id.toolbarTitle);
        TextView textView1=getActivity().findViewById(R.id.toolbarcontent);
        ListView listView =view.findViewById(R.id.settingsListView);
        textView.setVisibility(View.INVISIBLE);
        textView1.setText("Settings");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (id == 2)
                {

                    MainActivity.auth.signOut();
                    MainActivity.isLogin=false;
                    Intent intent = getActivity().getIntent();
                    getActivity().finish();
                    startActivity(intent);
                    Toast.makeText(getActivity(), "Logout Sucessfull", Toast.LENGTH_LONG).show();


                }

            }
        });


        MyAdapters myAdapters=new MyAdapters(Objects.requireNonNull(getActivity()),R.layout.row2, arrayList,3);

        listView.setAdapter(myAdapters);
        return  view;



    }


    }

