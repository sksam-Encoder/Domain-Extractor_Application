package com.example.domainextractor.DetailFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.domainextractor.MainActivity;
import com.example.domainextractor.R;


public class RenewFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_renew, container, false);
        MainActivity.toolbar.setVisibility(View.GONE);
        MainActivity.chip.setVisibility(View.GONE);
        ImageView close =view.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });



        return  view;
    }
}