package com.example.domainextractor.Chipfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.domainextractor.MainActivity;
import com.example.domainextractor.R;


public class Chip_Wishlist extends Fragment {


    public Chip_Wishlist(MainActivity mainActivity) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_chip__wishlist, container, false);

        TextView textView= getActivity().findViewById(R.id.toolbarTitle);
        TextView textView1=getActivity().findViewById(R.id.toolbarcontent);
        ListView listView =view.findViewById(R.id.settingsListView);
        textView.setVisibility(View.INVISIBLE);
        textView1.setText("WishList");
        return  view;
    }
}