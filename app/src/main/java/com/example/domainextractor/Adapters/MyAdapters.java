package com.example.domainextractor.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.domainextractor.Chipfragment.HomeFragment;
import com.example.domainextractor.DetailFragments.FragmentDetail1;

import com.example.domainextractor.DetailFragments.RenewFragment;
import com.example.domainextractor.R;

import java.util.ArrayList;

import static com.google.android.material.color.MaterialColors.getColor;

public class MyAdapters extends ArrayAdapter<String> {
    Activity context;
    ArrayList MainTitle;
    ArrayList subtitle;
    Integer image;
    public static Bundle bundle;
    ArrayList<String> arrayList;
    int Loc;


    public MyAdapters(@NonNull Activity context, int resource, ArrayList maintitle, ArrayList subtitle, Integer image, int i) {
        super(context, resource, maintitle);
        this.context = context;
        this.image = image;
        this.MainTitle = maintitle;
        this.subtitle = subtitle;
        Loc = i;
    }

    public MyAdapters(@NonNull Activity context, int resource, ArrayList<String> arrayList, int i) {
        super(context, resource, arrayList);
        this.context = context;
        this.arrayList = arrayList;
        Loc = i;
    }

    // For displaying the row dynamically in each row

    @SuppressLint({"ResourceAsColor", "Range"})
    public View getView(int position, View view, ViewGroup parent) {

        if (Loc == 1) {

            LayoutInflater layoutInflater = context.getLayoutInflater();
            @SuppressLint({"ViewHolder", "InflateParams"}) View view1 = layoutInflater.inflate(R.layout.row, null, true);

            ImageView imageView = view1.findViewById(R.id.imageView);
            ImageView imageView1 = view1.findViewById(R.id.sideImage);
            imageView1.setImageResource(R.drawable.right);
            TextView main = view1.findViewById(R.id.main);
            TextView sub = view1.findViewById(R.id.sub);
            main.setText((String) MainTitle.get(position));

            sub.setText((String)subtitle.get(position));

//  create onclick Listener as a method which return data in setOnItem click Listener
            view1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Dom = (String) MainTitle.get(position);
                    String Time = (String) subtitle.get(position);
                    bundle = new Bundle();
                    bundle.putString("key1", Dom);
                    bundle.putString("key2", Time);

                    Fragment fragment = new FragmentDetail1();
                    fragment.setArguments(bundle);

                    FragmentTransaction fragmentTransaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.chipMainFragment, fragment);
                    fragmentTransaction.addToBackStack("Home");
                    fragmentTransaction.commit();


                }
            });


            return view1;
        } else {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            @SuppressLint({"ViewHolder", "InflateParams"}) View view1;
            if (Loc == 2) {

                view1 = layoutInflater.inflate(R.layout.row, null, true);
                ImageView imageView = view1.findViewById(R.id.imageView);
                ImageView imageView1 = view1.findViewById(R.id.sideImage);
                Button btn = view1.findViewById(R.id.btn);
                @SuppressLint("UseSwitchCompatOrMaterialCode") Switch aSwitch = view1.findViewById(R.id.switchBar);
                String data = (String) MainTitle.get(position);

                if (data.equals("Auto renew") || data.equals("Transfer lock")) {

                    aSwitch.setVisibility(View.VISIBLE);

                } else {
                    if (position != 0) {
                        imageView1.setImageResource(R.drawable.right);
                    } else
                        btn.setVisibility(View.VISIBLE);

                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Fragment fragment= new RenewFragment();
                                FragmentTransaction fragmentTransaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.chipMainFragment, fragment);
                                fragmentTransaction.addToBackStack("detail");
                                fragmentTransaction.commit();
                            }
                        });


                }
                TextView main = view1.findViewById(R.id.main);
                TextView sub = view1.findViewById(R.id.sub);
                main.setText((String)MainTitle.get(position));
                sub.setText((String)subtitle.get(position));
                if (position==0)
                {

                     main.setTextColor(Color.parseColor("#FFFFFFFF"));
                     sub.setTextColor(Color.parseColor("#FFFFFFFF"));
                     view1.setBackgroundColor(Color.parseColor("#5a52e3"));

                }


            } else {
                view1 = layoutInflater.inflate(R.layout.row2, null, true);
                TextView main = view1.findViewById(R.id.Settname);
                ImageView imageView = view1.findViewById(R.id.imageView);
                main.setText(arrayList.get(position));
                String data = arrayList.get(position);
                if (data.equals("Support") || data.equals("Onetouch 2fa")) {

                    imageView.setVisibility(View.INVISIBLE);
                    main.setTextColor(R.color.Ltgrey);
                    view1.setBackgroundColor(Color.parseColor("#D3D3D3"));
                }


                if (data.equals("Log out")) {
                    main.setTextColor(Color.parseColor("#5a52e3"));
                    imageView.setVisibility(View.INVISIBLE);

                }


                main.setText(arrayList.get(position));


            }
            return view1;
        }
    }


    private int getColor(int grey) {

        return R.color.grey;


    }

}
