package com.example.domainextractor.Chipfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.domainextractor.Adapters.MyAdapters;
import com.example.domainextractor.MainActivity;
import com.example.domainextractor.R;

import java.util.Objects;


public class HomeFragment extends Fragment {

    TextView content;
    public static int Id = 1, i = 0;

    public ListView Domain;

    public HomeFragment(MainActivity mainActivity) {

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        content = getActivity().findViewById(R.id.toolbarcontent);
        Domain = view.findViewById(R.id.DomainName);
        content.setText("Home");




//        ref.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                ref.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//
//            }
//        });


        return view;
    }


}