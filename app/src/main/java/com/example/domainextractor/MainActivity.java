package com.example.domainextractor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.media.JetPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.domainextractor.Chipfragment.Chip_SearchFragment;
import com.example.domainextractor.Chipfragment.Chip_Wishlist;
import com.example.domainextractor.Chipfragment.HomeFragment;
import com.example.domainextractor.Chipfragment.SettingFragment;
import com.example.domainextractor.CustomnavFrag.ProjectFragment;
import com.example.domainextractor.CustomnavFrag.ServerFragment;
import com.example.domainextractor.CustomnavFrag.apiFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;
import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

import static com.example.domainextractor.Adapters.MyAdapters.bundle;

public class MainActivity extends AppCompatActivity {
    public static EditText ref;
    TextView title;
    TextView Log;
    ImageButton clear;
    TextView sample;
    public static LinearLayout customNav;
    int a[] = new int[4];
    public static Toolbar toolbar;
    public static ChipNavigationBar chip;
    public static Activity fa;
    public static FirebaseAuth auth;
    public static Boolean isLogin = false;
    MenuItem menuItem;
    Integer[] images;
    String[] Maintitle;
    String[] Subtitle;
    public static Integer ID = 0, test = -1;
    public static TextView main;
    int i,letters;
    public static FrameLayout frame;
    public static ImageView toolback, logo;


    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + getString(R.string.log) + "</font>"));
        toolbar = findViewById(R.id.my_awesome_toolbar);
        ref = findViewById(R.id.edt);
        logo = findViewById(R.id.logo);
        clear = findViewById(R.id.clear);
        frame = findViewById(R.id.chipMainFragment);
        fa = this;

        getSupportActionBar().hide();
        main = findViewById(R.id.toolbarcontent);
        title = findViewById(R.id.toolbarTitle);
        chip = findViewById(R.id.Navigation);
        auth = FirebaseAuth.getInstance();
        toolback = findViewById(R.id.toolbarback);

        customNav =findViewById(R.id.customnav);
        TextView nav = findViewById(R.id.firstnav);
        TextView secondnav = findViewById(R.id.secondnav);
        TextView firstnav = findViewById(R.id.firstnav);
        TextView thirdnav = findViewById(R.id.thirdnav);
        TextView fourthnav = findViewById(R.id.fourthnav);

        sample = firstnav;
        sample.setBackgroundResource(R.drawable.textlines);
        customNav.setVisibility(View.VISIBLE);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.chipMainFragment, new HomeFragment(MainActivity.this));
        fragmentTransaction.commit();


        //  MyAdapters myAdapters = new MyAdapters(this,R.layout.row,Maintitle, Subtitle,images,1);

        updateUi();








        secondnav.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if (ID != 1) {
                    MainActivity. ref.setVisibility(View.GONE);
                    MainActivity. chip.setVisibility(View.GONE);
                    secondnav.setBackgroundResource(R.drawable.textlines);


                    //                 GradientDrawable gd = new GradientDrawable();   // A drawable which can create a gradient colour for buttons ,drawable e.t.c;
//                    int color[]={R.color.black,R.color.white};
//                    gd.setColors(color);

                    sample.setBackgroundResource(0);
                    sample = secondnav;

                    a[1] = 1;
                    Fragment fragment = new ServerFragment();

                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    if (a[1] < ID) {
                        fragmentTransaction.setCustomAnimations(R.anim.enter_from_left_, R.anim.exit_to_right, R.anim.enter_from_right_fragment, R.anim.exit_from_left_fragment);

                    } else {
                        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right_fragment, R.anim.exit_from_left_fragment, R.anim.enter_from_left_, R.anim.exit_to_right);
                    }
                    ID = a[1];

                    fragmentTransaction.replace(R.id.chipMainFragment, new ServerFragment());
                    fragmentTransaction.addToBackStack("Home");

                    fragmentTransaction.commit();
                }
            }
        });
        firstnav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ID != 0) {
                    if (MainActivity.auth.getCurrentUser() != null) {
                        MainActivity. chip.setVisibility(View.VISIBLE);
                    }
                    firstnav.setBackgroundResource(R.drawable.textlines);

                    sample.setBackgroundResource(0);

                    sample = firstnav;

                    a[0] = 0;
                    Fragment fragment = new Chip_SearchFragment(MainActivity.this);

                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    if (a[0] < ID) {
                        fragmentTransaction.setCustomAnimations(R.anim.enter_from_left_, R.anim.exit_to_right, R.anim.enter_from_right_fragment, R.anim.exit_from_left_fragment);

                    } else {
                        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right_fragment, R.anim.exit_from_left_fragment, R.anim.enter_from_left_, R.anim.exit_to_right);
                    }
                    fragmentTransaction.replace(R.id.chipMainFragment, new HomeFragment(MainActivity.this));
                    ID = a[0];


                    int count = getSupportFragmentManager().getBackStackEntryCount();

                    for (int i = 0; i < count; i++) {
                        getSupportFragmentManager().popBackStack();
                    }
                }
            }
        });
        thirdnav.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (ID != 2) {
                    MainActivity.ref.setVisibility(View.GONE);

                    MainActivity.chip.setVisibility(View.GONE);

                    thirdnav.setBackgroundResource(R.drawable.textlines);

                    sample.setBackgroundResource(0);

                    sample = thirdnav;

                    a[2] = 2;

                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    if (a[2] < ID) {
                        fragmentTransaction.setCustomAnimations(R.anim.enter_from_left_, R.anim.exit_to_right, R.anim.enter_from_right_fragment, R.anim.exit_from_left_fragment);

                    } else {
                        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right_fragment, R.anim.exit_from_left_fragment, R.anim.enter_from_left_, R.anim.exit_to_right);
                    }
                    ID = a[2];
                    fragmentTransaction.replace(R.id.chipMainFragment, new apiFragment());
                    fragmentTransaction.addToBackStack("Home");

                    fragmentTransaction.commit();
                }
            }
        });
        fourthnav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ID != 3) {
                    MainActivity.ref.setVisibility(View.GONE);

                    MainActivity.chip.setVisibility(View.GONE);

                    fourthnav.setBackgroundResource(R.drawable.textlines);

                    sample.setBackgroundResource(0);

                    sample = fourthnav;

                    a[3] = 3;
                    ID = a[3];
                    Fragment fragment = new ProjectFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.enter_from_right_fragment, R.anim.exit_from_left_fragment, R.anim.enter_from_left_, R.anim.exit_to_right);
                    fragmentTransaction.replace(R.id.chipMainFragment, new ProjectFragment());
                    fragmentTransaction.addToBackStack("Home");

                    fragmentTransaction.commit();
                }
            }
        });












        chip.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {



                customNav.setVisibility(View.GONE);
                ref.setVisibility(View.GONE);
                logo.setVisibility(View.GONE);
                if (id == R.id.settings) {


                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.chipMainFragment, new SettingFragment(MainActivity.this));
                    fragmentTransaction.commit();


                }
                if (id == R.id.Search) {
                    logo.setVisibility(View.VISIBLE);
                    // main thing is that we need up to keep nav bar in the fragment instead of activity
                    // because it's changes in every fragment.
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.chipMainFragment, new Chip_SearchFragment(MainActivity.this));
                    fragmentTransaction.commit();
                    customNav.setVisibility(View.VISIBLE);

                }

                if (id == R.id.WishList) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.chipMainFragment, new Chip_Wishlist(MainActivity.this));
                    fragmentTransaction.commit();

                }

                if (id == R.id.home) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.chipMainFragment, new HomeFragment(MainActivity.this));
                    fragmentTransaction.commit();


                }

            }


        });


//    implementing Listeners----------------------------------------------------------------------










        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SlideFromDown.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slidefromdown, R.anim.slidefromup);


            }
        });

        ref.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                ref.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

            }
        });

        ref.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

                if (count == 2) {

                    clear.setVisibility(View.VISIBLE);
                    letters = count;

                }





            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub


            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub


            }
        });

        final int[] i = {0};
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.setText(null);
                if (i[0] ==1)
                {
                    ref.setVisibility(View.GONE);
                    main.setVisibility(View.VISIBLE);
                    clear.setVisibility(View.INVISIBLE);

                    i[0] =0;
                }
                else {
                    i[0]++;
                }
            }
        });

        KeyboardVisibilityEvent.setEventListener(this, new KeyboardVisibilityEventListener() {
            @Override
            public void onVisibilityChanged(boolean isOpen) {
                if (isOpen)
                {
                    chip.setVisibility(View.INVISIBLE);
                }
                else
                {

                    chip.setVisibility(View.VISIBLE);

                }
            }
        });



//    implementing Listeners---------------x-------------------------------------------x------------


    }//oncreate

    public void updateUi() {
        if (auth.getCurrentUser() != null) {
            main.setText("Home");
            chip.setVisibility(View.VISIBLE);
            title.setVisibility(View.INVISIBLE);
            chip.setItemSelected(R.id.home, true);

        } else {
            title.setVisibility(View.VISIBLE);
            title.setText("Login");
            chip.setVisibility(View.INVISIBLE);
        }


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.custom, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public void onBackPressed() {
        toolbar.setVisibility(View.VISIBLE);
        toolbar.setBackgroundColor(Color.parseColor("#5a52e3"));
        main.setText("Home");
        chip.setVisibility(View.VISIBLE);
        main.setTextColor(Color.parseColor("#FFFFFFFF"));
        toolback.setVisibility(View.GONE);
        super.onBackPressed();

    }


}


