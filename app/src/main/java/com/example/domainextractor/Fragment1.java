package com.example.domainextractor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Fragment1 extends Fragment {

      Button b1,b2;
       View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_1, container, false);
            b1 = view.findViewById(R.id.button);
            b2 = view.findViewById(R.id.clear);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction= getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.login_fragment,new Login());
                fragmentTransaction.addToBackStack("Main");
                fragmentTransaction.commit();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction= getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.login_fragment,new Reg_Fragment());
                fragmentTransaction.addToBackStack("Main");

                fragmentTransaction.commit();
            }
        });


         return view;

    }

}



