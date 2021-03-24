package com.example.domainextractor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends Fragment {
    EditText email, pass1, pass2;
    FirebaseAuth auth;
    ProgressBar progressBar;
    Button save;
    TextView stat;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        this.view = view;


        email = view.findViewById(R.id.email);
        pass1 = view.findViewById(R.id.pass1);
        save = view.findViewById(R.id.login);
        stat = view.findViewById(R.id.status);
        progressBar = view.findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();
        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // when all the creation of fragment and visiblity sucessfull.

        stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText resetMail = new EditText(v.getContext());
                final AlertDialog.Builder passReset= new AlertDialog.Builder(v.getContext());
                passReset.setTitle("Reset Password");
                passReset.setMessage("enter Your email to reset Password");
                passReset.setView(resetMail);
                 passReset.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                      String mail = resetMail.getText().toString();
                         auth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                             @Override
                             public void onSuccess(Void aVoid) {
                                 Toast.makeText(getActivity(), "Reset Link is sent to your Email", Toast.LENGTH_SHORT).show();
                             }
                         }).addOnFailureListener(new OnFailureListener() {
                             @Override
                             public void onFailure(@NonNull Exception e) {
                                 Toast.makeText(getActivity(), "Error Achived reset mail is not send"+e.getMessage(), Toast.LENGTH_SHORT).show();
                             }
                         });
                     }
                 });

                 passReset.setNegativeButton("No", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {

                     }
                 });
             passReset.create().show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String em = email.getText().toString();
                if (TextUtils.isEmpty(em)) {
                    email.setError("email is Required");
                    return;
                }


                String pass = pass1.getText().toString();
                if (TextUtils.isEmpty(pass)) {
                    pass1.setError("Password is Required");
                    return;
                }

                if (pass.length() < 6) {
                    pass1.setError("password Must be equal or greater than 6");
                    return;

                }

                progressBar.setVisibility(View.VISIBLE);

                auth.signInWithEmailAndPassword(em, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            stat.setText("Login Sucessfull");
                            MainActivity.isLogin = true;
                            Toast.makeText(getActivity(), "Login Sucessfull", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                            MainActivity.fa.finish();


                        } else {
                            Toast.makeText(getActivity(), "useranme or password is wrong", Toast.LENGTH_SHORT).show();
                            stat.setText(null);
                            progressBar.setVisibility(View.INVISIBLE);

                        }
                    }
                });
            }
        });
    }


}