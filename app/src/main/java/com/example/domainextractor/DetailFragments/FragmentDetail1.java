package com.example.domainextractor.DetailFragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.domainextractor.Adapters.MyAdapters;
import com.example.domainextractor.MainActivity;
import com.example.domainextractor.R;

public class FragmentDetail1 extends Fragment {

    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail1, container, false);

        Bundle bundle = this.getArguments();

        listView = view.findViewById(R.id.DomDetail);
        String[] detail = {bundle.getString("key1"), "DNS", "Whois", "Auto renew", "Transfer lock"};
        String[] subDetail = {bundle.getString("key2"), "Updtae DNS setting", "Update contct info", "Renew auto when Expire", "Lock domain from being transferred"};
        Integer image = R.id.sideImage;
        ListView listView = view.findViewById(R.id.DomDetail);
        //MyAdapters myAdapters = new MyAdapters(getActivity(), R.layout.row, detail, subDetail, image, 2);
        MainActivity.customNav.setVisibility(View.GONE);
     //   listView.setAdapter(myAdapters);
        MainActivity.toolbar.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        MainActivity.main.setText("Details");
        MainActivity.main.setTextColor(Color.parseColor("#5a52e3"));
        MainActivity.toolback.setVisibility(View.VISIBLE);
        MainActivity.ref.setVisibility(View.GONE);

        MainActivity.toolback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
// it return only main title not subTitle
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String selectedItem = (String) parent.getItemAtPosition(position);
//                Toast.makeText(getContext(), "item="+selectedItem, Toast.LENGTH_SHORT).show();
//            }
//        });


        return view;
    }



}
