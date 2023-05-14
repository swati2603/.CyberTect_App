package com.example.splash_screen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity
{
    public static final String TAG ="TAG";
    //EditText is a Widget of user interface (UI) used to retrieve and modify text data from a user in an Android app
    // adding functionalities
    FirebaseFirestore fStore;
    EditText nFullName,nEmail,nPassword,nPhone;
    Button nRegisterBtn;
    TextView nloginBtn;
    ProgressBar progressBar;

    String userid;
    FirebaseAuth fAuth;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        //defining all edit texts
        //Finds a view that was identified by the android:id
        nFullName = findViewById(R.id.fullname);
        nEmail = findViewById(R.id.email);
        nPassword = findViewById(R.id.password);
        nPhone = findViewById(R.id.phone_no);
        nRegisterBtn = findViewById(R.id.registerbtn);
        nloginBtn= findViewById(R.id.Createtext);
        progressBar = findViewById(R.id.progressbar);

        fAuth = FirebaseAuth.getInstance();
        fStore= FirebaseFirestore.getInstance();
        if(fAuth.getCurrentUser()!=null)
        {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        nloginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

        nRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email=nEmail.getText().toString().trim();
                String password= nPassword.getText().toString().trim();
                final String fullname= nFullName.getText().toString();
                final String phone= nPhone.getText().toString();
                if(TextUtils.isEmpty(fullname))
                {
                    nFullName.setError("Name is required");
                    return;
                }
                if(TextUtils.isEmpty(email))
                {
                    nEmail.setError("Email is Required");
                    return;
                }

                if(TextUtils.isEmpty(password))
                {
                    nPassword.setError("Password is Required");
                    return;
                }

                if(password.length()<6)
                {
                    nPassword.setError("Password must be 6 character");
                }

                progressBar.setVisibility(View.VISIBLE);

                //to save email and password
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            FirebaseUser fuser=fAuth.getCurrentUser();
                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(getApplicationContext(), "Register Successful", Toast.LENGTH_SHORT).show();
                                    startActivityLogin();

                                    //send verification
                                    FirebaseUser fuser= fAuth.getCurrentUser();
                                    fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(Register.this,"Verification Email has been Sent",Toast.LENGTH_SHORT).show();

                                        }
                                    });
                                    {

                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onfailure: Email not sent" + e.getMessage());
                                }
                            });

                            Toast.makeText(getApplicationContext(), "User created", Toast.LENGTH_SHORT).show();
                            userid = fAuth.getCurrentUser().getUid();
                            DocumentReference doc_ref= fStore.collection("user").document(userid);
                            Map<String,Object> user = new HashMap<>();
                            user.put("fName",fullname);
                            user.put("email",email);
                            user.put("Phone", phone);
                            doc_ref.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG, "onSuccess: User Profile is created" + userid);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: Email not sent" + e.getMessage());
                                }
                            });

                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else {
                            Toast.makeText(Register.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }
    private void startActivityLogin()
    {
        Intent intent= new Intent(Register.this,Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}