package com.example.domainextractor;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Reg_Fragment extends Fragment {
      EditText fname,lname,uname,email,pass1,pass2;
      FirebaseAuth auth;
      ProgressBar progressBar;
      Button save;
      TextView stat;
      View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view= inflater.inflate(R.layout.fragment_reg_, container, false);
          this.view=view;
          fname=view.findViewById(R.id.fname);
          lname=view.findViewById(R.id.lname);
          uname=view.findViewById(R.id.uname);
          email=view.findViewById(R.id.email);
          pass1=view.findViewById(R.id.pass1);
          pass2=view.findViewById(R.id.pass2);
          save=view.findViewById(R.id.save);
          stat    = view.findViewById(R.id.status);
              progressBar =view.findViewById(R.id.progressBar);
          auth=FirebaseAuth.getInstance();
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // when all the creation of fragment and visiblity sucessfull.
        if (auth.getCurrentUser()!=null)
        {
            startActivity(new Intent(getActivity(),MainActivity.class));
            Toast.makeText(getActivity(), "hellow", Toast.LENGTH_SHORT).show();
            getActivity().finish();

        }


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String name1= fname.getText().toString();
                if (TextUtils.isEmpty(name1))
                {
                    fname.setError("Name is Required");
                        return;
                }
                String name2=lname.getText().toString();
                if (TextUtils.isEmpty(name2))
                {
                    lname.setError(" last Name is Required");
                    return;
                }   String user=uname.getText().toString();
                if (TextUtils.isEmpty(user))
                {
                    uname.setError("user name is Required");
                    return;
                }

                String em=email.getText().toString();
                if (TextUtils.isEmpty(em))
                {
                    email.setError("Email is Required");
                    return;
                }

                String pass=pass1.getText().toString();
                if (TextUtils.isEmpty(pass))
                {
                    pass1.setError("Password is Required");
                    return;
                } String passconfirm=pass2.getText().toString();
                if (TextUtils.isEmpty(passconfirm))
                {
                    pass2.setError("password is Required");
                    return;
                }
                if (!pass.equals(passconfirm))

                {
                    pass2.setError("password don't match");
                    return;
                }
                if(pass.length()<6)
                {
                    pass2.setError("password Must be equal or greater than 6");
                    return;

                }

                progressBar.setVisibility(View.VISIBLE);

                auth.createUserWithEmailAndPassword(em,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                         if (task.isSuccessful())
                         {
                             stat.setVisibility(View.VISIBLE);
                             stat.setText("User Created");
                             Toast.makeText(getActivity(), "User Created", Toast.LENGTH_SHORT).show();
                             Intent intent=new Intent(getActivity(),MainActivity.class);
                             startActivity(intent);
                             getActivity().finish();

                         }
                         else
                         {
                             Toast.makeText(getActivity(), "Some Thing Went Worng", Toast.LENGTH_SHORT).show();


                         }
                    }
                });





            }
        });




    }
}